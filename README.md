# Comandos

aws cloudformation update-stack --stack-name database --template-body file://database.json --parameters file://parametros.json

Solo para IAM pide una configuracion especial
aws cloudformation create-stack --stack-name monolitico --template-body file://monolitico.json --capabilities CAPABILITY_IAM

aws cloudformation create-stack --stack-name sggroup --template-body file://scgroup.json

aws cloudformation update-stack --stack-name infraestructura --template-body file://vpc.json

aws cloudformation create-stack --stack-name frontend --template-body file://cloudfront.json

aws cloudformation create-stack --stack-name apigateway --template-body file://apigateway.json

para producccion
ng build --prod

esto es para subir al S3

subir carpeta dist