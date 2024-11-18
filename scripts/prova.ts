const values = [1, 2, 3, 4, 5, 6, 7, 8, 9];

// 1. array con solo numeri tra 3 e 7 inclusi
const filtered = values.filter((num) => num >= 3 && num <= 7);
console.log(filtered);

// 2. array con tutti i numeri raddoppiati

const results2 = values.map((num) => num * 2);
console.log(results2);

// 3. array con tutti i numeri pari raddoppiati

const filtered3 = values.filter((num) => num % 2 == 0).map((num) => num * 2);
console.log(filtered3);

// 4. somma di tutti i numeri

function sumF(n1: number, n2: number) {
  return n1 + n2;
}

const sumFC = (n1: number, n2: number) => n1 + n2;

const initialValue = 0;
const summ4 = values.reduce((acc, num) => acc + num, initialValue);
console.log(summ4);

// 5. somma di tutti i numeri pari

const result5 = values
  .filter((num) => num % 2 == 0)
  .reduce((acc, num) => acc + num, 0);

const result51 = values.reduce(
  (acc, num) => (num % 2 == 0 ? acc + num : acc),
  0
);

console.log(result5);
