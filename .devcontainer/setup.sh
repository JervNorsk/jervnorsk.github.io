#!/bin/bash
set -e

echo "🚀 Inizializzazione dell'ambiente di sviluppo..."

# -- Git configuration
git config --global --add safe.directory /workspaces/workspace
git config --global core.autocrlf input

# -- Installazione di Mise
curl https://mise.run | sh

# -- Configurazione di Mise
if ! grep -q "mise activate" ~/.bashrc; then
    echo "eval \"\$(/home/vscode/.local/bin/mise activate bash)\"" >> ~/.bashrc
fi
export PATH="/home/vscode/.local/share/mise/shims:/home/vscode/.local/bin:$PATH"
export MISE_EXPERIMENTAL=true

# -- Installazione delle dipendenze definite nel mise.toml
echo "📦 Installazione del toolchain tramite Mise..."
mise trust -y
mise install

echo "✅ Ambiente pronto. Puoi avviare il cluster con: mise run cluster:create"

