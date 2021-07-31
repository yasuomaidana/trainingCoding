// clear playground
function clear(): void {
    const input = document.querySelector('#clear-logs-button')
    if (input !== undefined && input !== null) {
        (input as HTMLElement).click()
    } else {
        console.log('clear not available')
    }
}

clear()

interface Cash{
    kind:"Cash" //tag
}
const Cash:Cash ={kind:"Cash"}
interface CreditCard{
    kind:"CreditCard"
    readonly bank:string
    readonly card:string
}
const CreditCard:(bank:string,card:string)=>CreditCard = 
(bank,card)=>({
    kind:"CreditCard",
    bank,
    card
})

type PaymentMethod = Cash|CreditCard
interface Payment{
    readonly amount:number
    readonly method:PaymentMethod   
}
const Payment:(amount:number,method:PaymentMethod)=>Payment=
(amount,method)=>({amount,method})

function matchPaymentMethod<T>
(onCash:()=>T,onCreditCard:(cc:CreditCard)=>T,pm:PaymentMethod):T{
    switch (pm.kind) {
        case "Cash": 
            return onCash()
        case "CreditCard":
            return onCreditCard(pm)
    }
}
function process(p:Payment):void{
    const pm:PaymentMethod = p.method
    matchPaymentMethod(()=>console.log(`paying with cash`),
    cc=> console.log(`paying with cc`),
    p.method)
}

const p1bad:Payment = Payment(30,Cash)
const p2bad:Payment = Payment(30,CreditCard("BOA","123213"))

process(p1bad)
process(p2bad)