#!/bin/sh

cd `dirname ${0}`

# ---
# env variable
namespace=naraway

# ---
# namespace
kubectl create namespace ${namespace}

# ---
# change default namespace
kubectl config set-context --current --namespace=${namespace}

# ---
# service account
kubectl apply -f resources/service-account.yml -n ${namespace}

# ---
# istio injection
kubectl label namespace ${namespace} istio-injection=enabled

# ---
# gateway
kubectl apply -f resources/gateway.yml -n ${namespace}

# ---
# external service
kubectl apply -f resources/external-maria.yml -n ${namespace}
kubectl apply -f resources/external-mongo.yml -n ${namespace}
kubectl apply -f resources/external-redis.yml -n ${namespace}
kubectl apply -f resources/external-kafka.yml -n ${namespace}

# ---
# secret
kubectl create secret generic address-book-maria-secret --from-literal='datasource.password=addressbook1234' -n ${namespace}
kubectl create secret generic address-book-mongo-secret --from-literal='datasource.password=addressbook1234' -n ${namespace}
