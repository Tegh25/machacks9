FROM python:3.8

WORKDIR /ai

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

EXPOSE 5000
CMD [ "flask", "run" ]