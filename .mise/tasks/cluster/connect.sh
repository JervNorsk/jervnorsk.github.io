#!/usr/bin/env bash
#MISE description = "Reconcile Kubernetes cluster configuration"
set -e

if ! k3d cluster list | grep -q "$K8S_CLUSTER_NAME"; then
  echo ""
  echo "----------------------------------------"
  echo "Cluster '$K8S_CLUSTER_NAME' not found. Operation aborted."
  echo "----------------------------------------"
  exit 1
else
  mkdir -p /home/vscode/.kube
  k3d kubeconfig get "$K8S_CLUSTER_NAME" > /home/vscode/.kube/config

  chmod 600 /home/vscode/.kube/config

  echo ""
  echo "----------------------------------------"
  echo "Testing cluster '$K8S_CLUSTER_NAME' connection:"
  echo "----------------------------------------"
  kubectl cluster-info
fi
