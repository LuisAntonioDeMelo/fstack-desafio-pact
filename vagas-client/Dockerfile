
#COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf 
FROM node:alpine as angular
WORKDIR /app
COPY package.json /app
RUN npm install --
COPY . .
RUN npm run build

FROM nginx:alpine
VOLUME /var/cache/nginx
COPY --from=angular /app/dist/vagas-client/browser /usr/share/nginx/html
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf