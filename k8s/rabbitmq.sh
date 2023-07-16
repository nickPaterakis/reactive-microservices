username="$(kubectl get secret rabbitmq-default-user -o jsonpath='{.data.username}' | base64 --decode)"
echo "username: $username"
password="$(kubectl get secret rabbitmq-default-user -o jsonpath='{.data.password}' | base64 --decode)"
echo "password: $password"

kubectl port-forward "service/rabbitmq" 15672
pause

#$SHELL

