#!/bin/bash
set -e

echo "=================================================="
echo "--> [1/4] Fixing Directory Permissions..."
echo "=================================================="
sudo mkdir -p /home/vscode/.local/bin \
             /home/vscode/.local/share/mise

sudo chown -R vscode:vscode /home/vscode/.local

echo "=================================================="
echo "--> [2/4] Configuring Git Settings..."
echo "=================================================="
git config --global --add safe.directory /workspaces/workspace || true
git config --global core.autocrlf input

echo "=================================================="
echo "--> [3/4] Installing/Verifying mise-en-place..."
echo "=================================================="
if [ ! -f "$HOME/.local/bin/mise" ]; then
    echo "Mise binary not found. Initiating clean installation..."
    curl https://mise.run | sh
else
    echo "Mise binary detected at $HOME/.local/bin/mise. Skipping network setup."
fi

if ! grep -q "mise activate" ~/.bashrc; then
    echo 'eval "$(/home/vscode/.local/bin/mise activate bash)"' >> ~/.bashrc
fi

export PATH="/home/vscode/.local/share/mise/shims:/home/vscode/.local/bin:$PATH"

echo "=================================================="
echo "--> [4/4] Executing Mise Toolchain Installation..."
echo "=================================================="
export MISE_TRUSTED_CONFIG_HOME=1
mise trust -y
mise install --verbose

echo "=================================================="
echo "--> Environment Setup Completed Successfully."
echo "=================================================="
