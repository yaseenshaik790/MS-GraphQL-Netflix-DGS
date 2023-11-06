# MS-GraphQL-Netflix-DGS

Please find below GraphQL URLs:

http://localhost:9993/graphiql

Create:

mutation CustomerMutation {
  createCustomer(input: {
    name: "Virat"
    gender: "m"
    contact: 987979
    mail: "ugu.@ga.com"
    accounts: [
      {
        accountNumber: 1212
        accountStatus: Active
        accountBalance: 1221.12
      }
    ]
  }){
    customerNumber
    name
    gender
    contact
    mail
  }
}

getAll Customer:
query CustomerQuery{
  
  getCustomers{
    customerNumber
    name gender
    contact
    mail
    
  }
}

Update Customer:

mutation CustomerMutation {
  updateCustomer(customerNumber: 1 
  name: "jack"){
    message
    customerNumber
  }
}

Delete Customer:

mutation CustomerMutation{
  
  deleteCustomer(customerNumber: 1){
    message
    customerNumber
  }
}
