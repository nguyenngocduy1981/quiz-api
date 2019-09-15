FROM mysql:5.7

COPY mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf
ADD db-setup.sql /docker-entrypoint-initdb.d

EXPOSE 3306
