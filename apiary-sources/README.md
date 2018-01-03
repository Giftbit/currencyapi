# Steps to compile

## You need to have python3.
1. Use `brew doctor` to ensure your homebrew is up to date. I had to update xcode etc.
2. Install python3: `brew install python3`
3. Install jinja2: `pip3 install jinja2`
4. Install pyyaml: `pip3 install pyyaml`

## Compile:
Navigate to `/apiary-sources` and run:
```
python3 release.py -v metadata.yaml -i master.md -o ../apiary.apib
```

