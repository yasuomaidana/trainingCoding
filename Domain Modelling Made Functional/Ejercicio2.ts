// payment: chas|credit card
// amount ##
// credit card: bank name, card number
// process-> diferent cash vs credit card
class Payment{
    amount: number
    isCash: boolean=true
    bank?: string
    card?: string
    constructor(amount:number){
        this.amount=amount
    }
}

const process:(p:Payment)=> void = p=>{
    if(p.isCash){
        console.log(`paying with cash: ${p.amount}`)
    }else{console.log(`charging card: ${p.card} charging bank: ${p.bank} paying with credit: ${p.amount}`)}
}

const p1cash: Payment = new Payment(123)
const p2cc: Payment = new Payment(100)
p2cc.isCash=false
p2cc.bank="BBVA"
p2cc.card="4242-4242-4242-4242"

process(p1cash)
process(p2cc)

const p3bad: Payment = new Payment(23)
p3bad.isCash = false

const p4bad: Payment = new Payment(28)
p4bad.bank="IXE"
p2cc.card="1111-2222-3333-4444"

process(p3bad)
process(p4bad)