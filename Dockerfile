FROM clojure:lein

WORKDIR /app

COPY . .

CMD [ "lein", "run" ]
