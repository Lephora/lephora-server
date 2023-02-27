db:
	chmod +x ./script/testdb.sh
	./script/testdb.sh

run.local:
	SPRING_PROFILES_ACTIVE=local ./gradlew bootRun

check.local:
	SPRING_PROFILES_ACTIVE=local ./gradlew clean check

jar:
	./gradlew bootJar

hooks:
	chmod -R +x .githooks
	git config core.hooksPath .githooks
