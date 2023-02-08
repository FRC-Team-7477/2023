#!/bin/sh
curl https://pyenv.run | bash
echo 'export PATH="$HOME/.pyenv/bin:$PATH"' >> ~/.bashrc
echo 'eval "$(pyenv init -)"' >> ~/.bashrc
pyenv install 3.8.2
pyenv global 3.8.2
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
python get-pip.py
pip install pre-commit
pre-commit install
