FROM python:3.9

WORKDIR /ai

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
RUN ["pip", "install", "--no-cache-dir", "-r", "requirement.txt"]

COPY . .

EXPOSE 5000
CMD [ "flask", "run", "--host", "0.0.0.0"]