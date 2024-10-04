# Dockerfile for React Frontend
FROM node:18 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package.json package-lock.json ./

# Install the dependencies
RUN npm install

# Copy the entire source code to the container
COPY ./ ./

# Build the React app
RUN npm run build

# Use a lighter image to serve the React app
FROM nginx:alpine

# Copy the built React files to NGINX
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80 for the React frontend
EXPOSE 80

# Start the NGINX server
CMD ["nginx", "-g", "daemon off;"]
