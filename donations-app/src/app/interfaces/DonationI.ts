import { CreditCardI } from './CreditCardI';

export interface DonationI {
    company: number,
    country: number,
    amount: number,
    creditCard: CreditCardI
}
