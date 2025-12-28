#!/bin/bash

# Script to push to GitHub using Personal Access Token
# Usage: ./push.sh YOUR_PERSONAL_ACCESS_TOKEN

if [ -z "$1" ]; then
    echo "Usage: ./push.sh YOUR_PERSONAL_ACCESS_TOKEN"
    echo ""
    echo "To create a Personal Access Token:"
    echo "1. Go to: https://github.com/settings/tokens"
    echo "2. Click 'Generate new token (classic)'"
    echo "3. Select 'repo' scope"
    echo "4. Generate and copy the token"
    exit 1
fi

TOKEN=$1
git push https://${TOKEN}@github.com/mishraharsh6244/Fitness-Monolith.git main

