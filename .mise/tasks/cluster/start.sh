#!/usr/bin/env bash
#MISE description = "Start Kubernetes cluster"
#MISE interactive = true
set -e

if ! k3d cluster list | grep -q "${K8S_CLUSTER_NAME}"; then
  echo ""
  echo "----------------------------------------"
  echo "Cluster '$K8S_CLUSTER_NAME' not found. Operation aborted."
  echo "----------------------------------------"
  exit 1
else
  echo ""
  echo "----------------------------------------"
  echo "Starting cluster '${K8S_CLUSTER_NAME}':"
  echo "----------------------------------------"
  k3d cluster start "${K8S_CLUSTER_NAME}"
fi
