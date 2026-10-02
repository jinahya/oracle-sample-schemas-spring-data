#!/bin/sh
# Regenerates the jOOQ classes of every schema module from the live database (the jooq-codegen
# profile of pom.xml). Needs the Oracle container: ../oracle-sample-schemas-persistence/_docker-compose-up.sh
#
# The password is SAMPLE_SCHEMAS_PASSWORD, as for application.yaml; `password` if unset.
set -e
cd "$(dirname "$0")"
mvn -q -P jooq-codegen \
  -Djooq-codegen.password="${SAMPLE_SCHEMAS_PASSWORD:-password}" \
  generate-test-sources
