document.addEventListener("DOMContentLoaded", () => {
    const step1 = document.getElementById("step1");
    const step2 = document.getElementById("step2");
    const step3 = document.getElementById("step3");
    const submitButton = document.querySelector(".submit-btn");
    const tabs = document.querySelectorAll('.tab');
    const steps = [step1, step2, step3];

    let currentStep = 1;

    // Function to populate recap content
    function populateRecap(step) {
        const recapTable = document.getElementById('recapTable');
        recapTable.innerHTML = '';

        // Retrieve data from localStorage
        const project = localStorage.getItem('project') || 'No project found';
        const who = localStorage.getItem('who') || 'No information found';
        const amount = localStorage.getItem('amount') || 'Not specified';
        const duration = localStorage.getItem('duration') || 'Not specified';
        const monthly = localStorage.getItem('monthly') || 'Not specified';
        const email = localStorage.getItem('email') || 'No email provided';
        const phone = localStorage.getItem('phone') || 'No phone number provided';

        // Step 1
        if (step === 1) {
            recapTable.innerHTML += `
                <thead>
                    <tr>
                        <td class="text-primary" id="recapProject">My Project</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="text-primary-body">${project}</td> <!-- project from local storage -->
                    </tr>
                </tbody>
            `;
        }
        // Step 2
        else if (step === 2) {
            recapTable.innerHTML += `
                <thead>
                    <tr>
                        <td class="text-primary" id="recapProject">My Project</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="text-primary-body">${project}</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <td class="text-primary">Contact Details and Personal Information</td>
                    </tr>
                </thead>
                <tbody>
                    <tr class="recap-item">
                        <td class="text-secondary">You are:</td>
                        <td class="text-primary-body">${who}</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">Amount:</td>
                        <td class="text-primary-body">${amount} DH</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">Duration:</td>
                        <td class="text-primary-body">${duration} Months</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">Mensuality:</td>
                        <td class="text-primary-body">${monthly} DH</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">File fee:</td>
                        <td class="text-primary-body">165.00 DH</td>
                    </tr>
                </tbody>
            `;
        }
        // Step 3
        else if (step === 3) {
            recapTable.innerHTML += `
                <thead>
                    <tr>
                        <td class="text-primary" id="recapProject">My Project</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="text-primary-body">${project}</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <td class="text-primary">Details of My Credit</td>
                    </tr>
                </thead>
                <tbody>
                    <tr class="recap-item">
                        <td class="text-secondary">Email:</td>
                        <td class="text-primary-body">${email}</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">Phone:</td>
                        <td class="text-primary-body">${phone}</td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <td class="text-primary">Contact Details and Personal Information</td>
                    </tr>
                </thead>
                 <tbody>
                    <tr class="recap-item">
                        <td class="text-secondary">You are:</td>
                        <td class="text-primary-body">${who}</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">Amount:</td>
                        <td class="text-primary-body">${amount} DH</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">Duration:</td>
                        <td class="text-primary-body">${duration} Months</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">Mensuality:</td>
                        <td class="text-primary-body">${monthly} DH</td>
                    </tr>
                    <tr class="recap-item">
                        <td class="text-secondary">File fee:</td>
                        <td class="text-primary-body">165.00 DH</td>
                    </tr>
                </tbody>
            `;
        }
    }

    // Update the tab colors based on the current step
    function updateTabs(step) {
        tabs.forEach((tab, index) => {
            if (index + 1 === step) {
                tab.classList.add('active');
                tab.classList.remove('completed');
            } else if (index + 1 < step) {
                tab.classList.add('completed');
                tab.classList.remove('active');
            } else {
                tab.classList.remove('active', 'completed');
            }
        });
    }

    // Display the correct form step
    function showStep(step) {
        steps.forEach((stepDiv, index) => {
            stepDiv.style.display = (index + 1 === step) ? 'block' : 'none';
        });
        updateTabs(step);
        populateRecap(step);
    }

    // Initialize with first step
    showStep(currentStep);


    submitButton.addEventListener("click", (e) => {
        e.preventDefault();
        if (currentStep < steps.length) {
            currentStep++;
            showStep(currentStep);
        }
    });
});
