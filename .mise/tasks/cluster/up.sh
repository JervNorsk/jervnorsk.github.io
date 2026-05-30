#!/usr/bin/env bash
#MISE description = "Create a lightweight Kubernetes cluster"
#MISE interactive = true
#USAGE flag "-f --force" help=""
set -e

if k3d cluster list | grep -q "${K8S_CLUSTER_NAME}"; then
  if [ "${usage_force:-false}" = "true" ]; then
    mise ops:k8s:cluster:down
  else
    mise ops:k8s:cluster:connect
  fi
else
  echo ""
  echo "----------------------------------------"
  echo "Creating cluster '${K8S_CLUSTER_NAME}':"
  echo "----------------------------------------"
  k3d cluster create "${K8S_CLUSTER_NAME}" --wait -p "51820:51820/UDP@loadbalancer"

  echo ""
  echo "----------------------------------------"
  echo "Testing cluster '${K8S_CLUSTER_NAME}' connection:"
  echo "----------------------------------------"
  kubectl cluster-info
fi
