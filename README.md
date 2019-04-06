# Comandos

aws cloudformation update-stack --stack-name database --template-body file://database.json --parameters file://parametros.json

Solo para IAM pide una configuracion especial
aws cloudformation create-stack --stack-name monolitico --template-body file://monolitico.json --capabilities CAPABILITY_IAM

aws cloudformation create-stack --stack-name sggroup --template-body file://scgroup.json

aws cloudformation update-stack --stack-name infraestructura --template-body file://vpc.json