#!/usr/bin/env bash
#MISE description = "Destroy Kubernetes cluster"
#MISE interactive = true
set -e

if ! k3d cluster list | grep -q "${K8S_CLUSTER_NAME}"; then
  exit 0
else
  echo ""
  echo "----------------------------------------"
  echo "Deleting cluster '$K8S_CLUSTER_NAME':"
  echo "----------------------------------------"
  k3d cluster delete "$K8S_CLUSTER_NAME"
fi
