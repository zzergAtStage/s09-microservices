#!/bin/bash

# Running
cd ..

echo "Running Prometheus..."
docker run -d \
    -p 9091:9090 \
    -v /tmp/prometheus.yaml:/etc/prometheus/prometheus.yml \
    prom/prometheus
