# base image
FROM node:current-alpine3.18

# set working directory
WORKDIR /vue_app

EXPOSE 8080

# add `/node_modules/.bin` to $PATH
ENV PATH /vue_app/node_modules/.bin:$PATH

#COPY /vue_app/package.json ./package.json
COPY /vue_app .

RUN npm init --yes
RUN npm install
RUN npm install @vue/cli@5.0.8 -g

# start app
CMD ["npm", "run", "serve"]