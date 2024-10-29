# build stage
FROM node:current-alpine3.18 as build-stage

# set working directory
WORKDIR /vue_app

EXPOSE 8081

# add `/node_modules/.bin` to $PATH
ENV PATH /vue_app/node_modules/.bin:$PATH

COPY /vue_app/package.json ./package.json


RUN npm init --yes
RUN npm install
RUN npm install @vue/cli@5.0.8 -g

# build app
# CMD ["npm", "run", "build"]

COPY /vue_app .
RUN npm run build

# Use the official nginx image as the base image for serving the application
FROM nginx:stable-alpine as production-stage

# Copy the built files from the build-stage to the nginx html directory
COPY --from=build-stage /vue_app/dist /usr/share/nginx/html

# Copy custom nginx configuration files
COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 81
EXPOSE 81

# Start Nginx to serve the application
CMD ["nginx", "-g", "daemon off;"]