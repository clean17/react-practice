# 이미지를 Node.js 14 버전으로 설정
FROM node:14.18.1-bullseye

# 작업 디렉토리를 /app 으로 설정
WORKDIR /home/node/app

# 프로젝트의 package.json 및 package-lock.json을 Docker 이미지에 복사
COPY package*.json ./

RUN npm cache clean --force

RUN npm install

# 앱 소스를 Docker 이미지에 복사
COPY . .

# 포트 3000번을 외부에 노출
EXPOSE 3000

# 애플리케이션 실행
CMD ["npm", "start"]    


# 이미지 빌드 (이미지 이름은 my-react-app으로 설정)
# docker build -t my-react-app .

# 컨테이너 실행 (외부 3000번 포트와 연결)
# docker run -p 3000:3000 my-react-app

# 컨테이너 생성 및 실행
# docker run --name my-react-app-con -v "$(pwd):/home/node/app" -p 3000:3000 my-react-app