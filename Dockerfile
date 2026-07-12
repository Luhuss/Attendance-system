FROM ubuntu:latest
LABEL authors="luismaraboli"

ENTRYPOINT ["top", "-b"]