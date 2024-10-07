// Interest rate
const interestRate = 0.12;

const minDuration = 12;
const maxDuration = 120;
const minAmount = 5000;
const maxAmount = 600000;

// Track elements

// ***Step 1***
const projectSelect = document.getElementById('project');
const whoSelect = document.getElementById('who');
const amountRange = document.getElementById('amount');
const amountNumber = document.getElementById('amount-number');
const durationRange = document.getElementById('duration');
const durationNumber = document.getElementById('duration-number');
const monthlyRange = document.getElementById('monthly');
const monthlyNumber = document.getElementById('monthly-number');

// ***Step 2***
const emailInput = document.getElementById('email');
const phoneInput = document.getElementById('phone');

// ***Step 3***
const civilityInputs = document.getElementsByName('civility');
const lastNameInput = document.getElementById('last-name');
const firstNameInput = document.getElementById('first-name');
const cinInput = document.getElementById('cin');
const birthdayInput = document.getElementById('birthday');
const hiringDayInput = document.getElementById('hiring-day');
const incomeInput = document.getElementById('income');
const haveCreditYes = document.getElementById('have-credit-yes');
const haveCreditNo = document.getElementById('have-credit-no');

// Default values
const defaultValues = {
    project: 'Personal_Loan',
    who: 'EMPLOYEES',
    amount: '10000',
    duration: '24',
    monthly: '466.67'
};

// Calculation functions
function calculateMonthlyPayment(amount, duration) {
    const totalAmount = amount * (1 + interestRate);
    const monthlyPayment = totalAmount / duration;

    return isNaN(monthlyPayment) ? 0 : monthlyPayment.toFixed(2);
}

function calculateDuration(amount, monthlyPayment) {
    const totalAmount = amount * (1 + interestRate);
    const duration = totalAmount / monthlyPayment;
    return Math.ceil(duration);
}

function calculateMinMaxMonthly(amount) {
    const totalAmount = amount * (1 + interestRate);
    const maxMonthly = totalAmount / minDuration;
    const minMonthly = totalAmount / maxDuration;
    return {
        min: minMonthly.toFixed(2),
        max: maxMonthly.toFixed(2)
    };
}

function validateRange(value, min, max) {
    return Math.min(Math.max(value, min), max);
}

function updateMonthlyPayment() {
    let amount = parseFloat(amountNumber.value);
    let duration = parseFloat(durationNumber.value);

    if (isNaN(amount) || isNaN(duration)) {
        return;
    }

    amount = validateRange(amount, minAmount, maxAmount);
    duration = validateRange(duration, minDuration, maxDuration);

    const monthlyPayment = calculateMonthlyPayment(amount, duration);
    const { min, max } = calculateMinMaxMonthly(amount);

    monthlyRange.min = min;
    monthlyRange.max = max;

    const validatedMonthlyPayment = validateRange(monthlyPayment, min, max);
    monthlyNumber.value = validatedMonthlyPayment;
    monthlyRange.value = validatedMonthlyPayment;

    // Store the updated monthly payment range value to local storage
    saveToLocalStorage('monthly', validatedMonthlyPayment);
}

function updateDuration() {
    let amount = parseFloat(amountNumber.value);
    let monthlyPayment = parseFloat(monthlyNumber.value);

    amount = validateRange(amount, minAmount, maxAmount);
    let newDuration = calculateDuration(amount, monthlyPayment);
    newDuration = validateRange(newDuration, minDuration, maxDuration);
    durationNumber.value = newDuration;
    durationRange.value = newDuration;

    // Store the updated duration value to local storage
    saveToLocalStorage('duration', newDuration);
}

// Sync the number and the range of the amount inputs
amountRange.addEventListener('input', function() {
    amountNumber.value = amountRange.value;
    updateMonthlyPayment();

    // Store amount range value to local storage
    saveToLocalStorage('amount', amountRange.value);
});

amountNumber.addEventListener('input', function() {
    amountRange.value = amountNumber.value;
    updateMonthlyPayment();
});

// Sync the number and the range of the duration inputs
durationRange.addEventListener('input', function() {
    durationNumber.value = durationRange.value;
    updateMonthlyPayment();

    // Store duration range value to local storage
    saveToLocalStorage('duration', durationRange.value);
});

durationNumber.addEventListener('input', function() {
    durationRange.value = durationNumber.value;
    updateMonthlyPayment();
});

// Sync the number and the range of the monthly payment input
monthlyRange.addEventListener('input', function() {
    monthlyNumber.value = monthlyRange.value;
    updateDuration();

    // Store monthly range value to local storage
    saveToLocalStorage('monthly', monthlyRange.value);
});

monthlyNumber.addEventListener('input', function() {
    monthlyRange.value = monthlyNumber.value;
    updateDuration();
});

// Initial calculation on page load
window.addEventListener('load', function() {
    loadFromLocalStorage(); // Load previous values
    updateMonthlyPayment();
});

// Store on localStorage
function saveToLocalStorage(key, value) {
    localStorage.setItem(key, value);
}

// Listeners for each input to store their values on change
projectSelect.addEventListener('change', () => {
    const selectedValue = projectSelect.value || defaultValues.project;
    saveToLocalStorage('project', selectedValue);
});

whoSelect.addEventListener('change', () => saveToLocalStorage('who', whoSelect.value));
amountNumber.addEventListener('input', () => saveToLocalStorage('amount', amountNumber.value));
durationNumber.addEventListener('input', () => saveToLocalStorage('duration', durationNumber.value));
monthlyNumber.addEventListener('input', () => saveToLocalStorage('monthly', monthlyNumber.value));
emailInput.addEventListener('input', () => saveToLocalStorage('email', emailInput.value));
phoneInput.addEventListener('input', () => saveToLocalStorage('phone', phoneInput.value));
lastNameInput.addEventListener('input', () => saveToLocalStorage('lastName', lastNameInput.value));
firstNameInput.addEventListener('input', () => saveToLocalStorage('firstName', firstNameInput.value));
cinInput.addEventListener('input', () => saveToLocalStorage('cin', cinInput.value));
birthdayInput.addEventListener('change', () => saveToLocalStorage('birthday', birthdayInput.value));
hiringDayInput.addEventListener('change', () => saveToLocalStorage('hiringDay', hiringDayInput.value));
incomeInput.addEventListener('input', () => saveToLocalStorage('income', incomeInput.value));

civilityInputs.forEach(input => {
    input.addEventListener('change', () => {
        if (input.checked) {
            saveToLocalStorage('civility', input.value);
        }
    });
});

haveCreditYes.addEventListener('change', () => {
    if (haveCreditYes.checked) {
        saveToLocalStorage('haveCredit', 'yes');
    }
});

haveCreditNo.addEventListener('change', () => {
    if (haveCreditNo.checked) {
        saveToLocalStorage('haveCredit', 'no');
    }
});

// Load data from local storage
function loadFromLocalStorage() {
    projectSelect.value = localStorage.getItem('project') || defaultValues.project;
    whoSelect.value = localStorage.getItem('who') || defaultValues.who;
    amountNumber.value = localStorage.getItem('amount') || defaultValues.amount;
    amountRange.value = localStorage.getItem('amount') || defaultValues.amount;
    durationNumber.value = localStorage.getItem('duration') || defaultValues.duration;
    durationRange.value = localStorage.getItem('duration') || defaultValues.duration;
    monthlyNumber.value = localStorage.getItem('monthly') || defaultValues.monthly;
    monthlyRange.value = localStorage.getItem('monthly') || defaultValues.monthly;
    emailInput.value = localStorage.getItem('email') || '';
    phoneInput.value = localStorage.getItem('phone') || '';
    lastNameInput.value = localStorage.getItem('lastName') || '';
    firstNameInput.value = localStorage.getItem('firstName') || '';
    cinInput.value = localStorage.getItem('cin') || '';
    birthdayInput.value = localStorage.getItem('birthday') || '';
    hiringDayInput.value = localStorage.getItem('hiringDay') || '';
    incomeInput.value = localStorage.getItem('income') || '';

    const civility = localStorage.getItem('civility');
    if (civility) {
        document.querySelector(`input[name="civility"][value="${civility}"]`).checked = true;
    }

    const haveCredit = localStorage.getItem('haveCredit');
    if (haveCredit === 'yes') {
        haveCreditYes.checked = true;
    } else if (haveCredit === 'no') {
        haveCreditNo.checked = true;
    }
}

// Clear local storage upon final submission or as needed
function clearLocalStorage() {
    localStorage.clear(); // Or you can selectively remove keys if needed
}
