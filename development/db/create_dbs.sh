#!/bin/bash
set -e

MYSQL="mysql --user=${MYSQL_USER} --password=${MYSQL_PASSWORD}"

echo "Creating database: ${DB_APP_NAME}"
echo "Creating test database: ${DB_APP_NAME}-test"

$MYSQL <<EOSQL
CREATE DATABASE \`${DB_APP_NAME}\`;
GRANT ALL PRIVILEGES ON \`${DB_APP_NAME}\`.* TO '${MYSQL_USER}'@'%';

CREATE DATABASE \`${DB_APP_NAME}-test\`;
GRANT ALL PRIVILEGES ON \`${DB_APP_NAME}-test\`.* TO '${MYSQL_USER}'@'%';

FLUSH PRIVILEGES;
EOSQL