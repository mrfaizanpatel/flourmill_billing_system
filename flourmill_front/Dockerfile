# Stage 1: Build the Angular app
FROM node:16 AS build

# Set the working directory for your Angular app
WORKDIR /app

# Install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the frontend code
COPY . .

# Build the Angular app
RUN npm run build --prod

# Stage 2: Serve the Angular app with Nginx
FROM nginx:alpine

# Copy the built Angular app from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80 for the frontend
EXPOSE 80

# Use the default Nginx config to serve the app
CMD ["nginx", "-g", "daemon off;"]
