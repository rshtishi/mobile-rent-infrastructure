FROM rabbitmq:3-management

# Set environment variables (optional)
ENV RABBITMQ_DEFAULT_USER=admin
ENV RABBITMQ_DEFAULT_PASS=password

# Expose ports
EXPOSE 5672 15672

# Start the RabbitMQ server
CMD ["rabbitmq-server"]