FROM mysql:5.7

# 도커환경에서 컨테이너 생성시 스크립트 참조
COPY ./scripts/ /docker-entrypoint-initdb.d/
COPY ./01-database.sh /docker-entrypoint-initdb.d/
# 래퍼런스 - https://zionlee.tistory.com/54
COPY ./my.cnf /etc/mysql/conf.d/my.cnf

RUN sed -i 's/\r$//' /docker-entrypoint-initdb.d/01-database.sh
RUN chmod 644 /etc/mysql/conf.d/my.cnf
RUN chmod 755 /docker-entrypoint-initdb.d/01-database.sh

EXPOSE 3306

#ENV MYSQL_USER=${HS512_SECRET} \
#    MYSQL_PASSWORD=${HS512_SECRET} \
#    MYSQL_ROOT_PASSWORD=1125 \
#    MYSQL_DATABASE=${HS512_SECRET}


# 이미지 빌드 명령어 (현 파일과 같은 디렉토리에서)
# docker build -t database-img .

# 실행 명령어 (터미널에 로그 찍히는 것 보기)
# docker run --name {컨테이너명(지정해주면 됨)} -p 3306:3306 {이미지명}
# docker run -v mysql-volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${HS512_SECRET} database-img   ( 볼륨 사용 시 )

# 컨테이너 확인 docker ps -a
# docker run --name my-app-database-2 -p 3306:3306 database-img

# 실행 명령어 (데몬으로 실행) d 붙으며 백그라운드 모드
# docker run --name {컨테이너명} -p 3306:3306 -d {이미지명}
# docker run --name mysql-con -p 3306:3306 -d database-img