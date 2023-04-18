FROM python:3.9

WORKDIR /ai

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 5000
RUN  flask run --host 0.0.0.0
# Flask isn't exposed properly outside of the container, and since the service needs to be started by
# visiting the site anyway, curling the site is a good work-around.
CMD [ "curl", "127.0.0.1:5000"] 