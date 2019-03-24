FROM node:9.6.1-alpine as node

WORKDIR /usr/src/app

COPY package.json /usr/src/app/package.json
RUN npm install
RUN npm install -g @angular/cli@1.7.1

# add app
COPY . /usr/src/app
RUN ng build --prod

# start app
FROM nginx:1.13.12-alpine
RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*
COPY --from=node /usr/src/app/dist/* /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/nginx.conf
