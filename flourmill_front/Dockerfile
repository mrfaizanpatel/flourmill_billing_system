# Stage 1: Build Angular app
FROM ubuntu:22.04 AS build

# Install Node.js, npm, and Angular CLI
RUN apt-get update && apt-get install -y curl gnupg \
  && curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
  && apt-get install -y nodejs \
  && npm install -g @angular/cli

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY flourmill_front/package*.json ./
RUN npm install

# Copy all project files
COPY flourmill_front/ .

# Build Angular app
RUN ng build --configuration=production

# Stage 2: Serve with Nginx
FROM ubuntu:22.04

# Install nginx
RUN apt-get update && apt-get install -y nginx

# Remove default nginx web files
RUN rm -rf /var/www/html/*

# Copy Angular built files
COPY --from=build /app/dist/flourmill_front /var/www/html

# Configure nginx to handle Angular routing
RUN echo 'server { \
    listen 80; \
    server_name localhost; \
    root /var/www/html; \
    index index.html; \
    location / { \
        try_files $uri $uri/ /index.html; \
    } \
}' > /etc/nginx/sites-available/default

# Expose port 80 inside container
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
