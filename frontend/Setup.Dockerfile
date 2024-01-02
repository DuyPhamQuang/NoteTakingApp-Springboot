FROM node:current-alpine3.18

WORKDIR /vue-setup

RUN npm install -g @vue/cli

# The following commands ensure access to our files
# If we left them out, changing files on our local setup
# would fail due to insufficient permissions.
#RUN userdel -r node

RUN addgroup -S docker

RUN adduser -S --shell /bin/bash --ingroup docker vscode

# Set the active user and open the interactive terminal
# USER user

ENTRYPOINT ["docker-entrypoint.sh"]