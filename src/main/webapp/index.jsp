<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="./styles/styles.css">
    <title>WafaSalaf</title>
</head>
<body>
<section>
    <!-- Header -->
    <h1>Apply for my credit online</h1>
    <div class="container">
        <a class="back-link" href="#"><i class="fa-solid fa-arrow-left"></i> Back</a>
        <div class="container--parts">
            <!-- tabs -->
            <div class="container--left">
                <div class="container--left__tabs">
                    <div class="tab">
                        <span>1</span>
                        Simulate my credit
                    </div>
                    <div class="tab">
                        <span>2</span>
                        My coordinates
                    </div>
                    <div class="tab">
                        <span>3</span>
                        My personal info
                    </div>
                </div>
                <!-- form -->
                <div class="container--left__form">
                    <form action="<%= request.getContextPath() %>/request" method="POST">
                        <div class="step1" id="step1">
                            <div class="options">
                                <label for="project">My project</label><br>
                                <select name="project" id="project">
                                    <option value="Personal_Loan">I need money</option>
                                    <option value="CAR_RENT">I am financing my used vehicle</option>
                                    <option value="Revolving">I manage my unforeseen events</option>
                                    <option value="CAR_CREDIT">I am financing my new vehicle</option>
                                    <option value="HOUSEHOLD_EQUIPMENT">I equip my house</option>
                                </select><br>
                            </div>
                            <div class="options">
                                <label for="who">I'm</label><br>
                                <select name="who" id="who">
                                    <option value="EMPLOYEES">Employees in the private sector</option>
                                    <option value="Functionary">Functionary</option>
                                    <option value="PROFESSION">Liberal profession</option>
                                    <option value="TRADESMAN">Trades man</option>
                                    <option value="CRAFTSMAN">Crafts man</option>
                                    <option value="RETIRED">Retired</option>
                                    <option value="OTHER_PROFESSIONS">Other professions</option>
                                </select>
                            </div>
                            <div class="simulator">
                                <div class="slider">
                                    <label for="amount">Amount (in DH)</label>
                                    <input class="number" type="number" id="amount-number" name="amount" value="10000">
                                </div>
                                <input class="range" type="range" id="amount" name="amount-range" min="5000" max="600000">
                            </div>
                            <div class="simulator">
                                <div class="slider">
                                    <label for="duration">Duration (in months)</label>
                                    <input class="number" type="number" id="duration-number" name="duration" value="24">
                                </div>
                                <input class="range" type="range" id="duration" name="duration-range" min="12" max="120" >
                            </div>
                            <div class="simulator">
                                <div class="slider">
                                    <label for="monthly">Monthly (in DH)</label>
                                    <input class="number" type="number" id="monthly-number" name="monthly" value="445.93">
                                </div>
                                <input class="range" type="range" id="monthly" name="monthly-range" >
                            </div>
                        </div>
                        <div class="step2" id="step2">
                            <div class="step2-item">
                                <input type="email" id="email" name="email" placeholder="Email*">
                                <label for="email">Email*</label>
                            </div>
                            <div class="step2-item">
                                <input type="text" id="phone" name="phone" placeholder="Mobile phone*">
                                <label for="phone">Mobile phone*</label>
                            </div>
                        </div>
                        <div class="step3" id="step3">
                            <div class="step3-item1">
                                <label for="civility">Civility</label>
                                <div class="choices">
                                    <div class="choice">
                                        <input type="radio" id="madam" name="civility" value="MADAM">
                                        <label for="madam">Madam</label>
                                    </div>
                                    <div class="choice">
                                        <input type="radio" id="miss" name="civility" value="MISS">
                                        <label for="miss">Miss</label>
                                    </div>
                                    <div class="choice">
                                        <input type="radio" id="mr" name="civility" value="MR">
                                        <label for="mr">Mr</label>
                                    </div>
                                </div>
                            </div>

                            <div class="step3-item">
                                <input type="text" id="last-name" name="last-name" placeholder="Last Name*" required>
                                <label for="last-name">Last Name*</label>
                            </div>

                            <div class="step3-item">
                                <input type="text" id="first-name" name="first-name" placeholder="First Name*" required>
                                <label for="first-name">First Name*</label>
                            </div>

                            <div class="step3-item">
                                <input type="text" id="cin" name="cin" placeholder="CIN number /Courtesy card*" required>
                                <label for="cin">CIN number /Courtesy card*</label>
                            </div>

                            <div class="step3-item">
                                <input type="date" id="birthday" name="birthday" value="2002-10-02" required>
                                <label for="birthday">Date of birth DD/MM/YYYY*</label>
                            </div>

                            <div class="step3-item">
                                <input type="date" id="hiring-day" name="hiring-day"  value="2023-09-10" required>
                                <label for="hiring-day">Date of hiring/start of the activity DD/MM/YYYY*</label>
                            </div>

                            <div class="step3-item">
                                <input type="number" id="income" name="income" placeholder="Total monthly income (net in DH)*" required>
                                <label for="income">Total monthly income (net in DH)*</label>
                            </div>

                            <div class="step3-item8">
                                <label for="have-credit">Do you have credit?</label>
                                <div class="choice">
                                    <input type="radio" id="have-credit-yes" name="have-credit" value="yes"><span> Yes</span>
                                    <input type="radio" id="have-credit-no" name="have-credit" value="no"><span> No</span>
                                </div>
                            </div>

                            <div class="step3-item-terms">
                                <input type="checkbox" id="terms" name="terms" required>
                                <label for="terms">
                                    <p>I have read and accept the <a href="https://www.wafasalaf.ma/fr/cgu">general terms</a> of use appearing on the legal information, in particular the mention relating to the protection of personal data.</p>
                                </label>
                            </div>
                        </div>
                        <button type="submit" class="submit-btn">Continue <br><span>without commitment</span></button>
                    </form>
                    <div class="footer_form">
                        <p>
                            Simulation for information purposes and not contractual. The minimum monthly payment is 180 dirhams. A Wafasalaf customer can benefit from a more advantageous pricing based on their preferred terms.
                        </p>
                        <br>
                        <p>
                            In accordance with Law 09-08, you have a right of access, rectification and opposition to the processing of your personal data. This processing is authorized by the CNDP under number A-GC-206/2014.
                        </p>
                    </div>
                </div>
            </div>
            <!-- recap -->
            <div class="container--right">
                <h2>My recap</h2>
                <table class="recap-table" id="recapTable">

                </table>
            </div>
        </div>
</section>

<script src="./js/form.js"></script>
<script src="./js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('status') && urlParams.get('status') === 'success') {
        Swal.fire({
            title: 'Success!',
            text: 'Your request has been successfully created.',
            icon: 'success',
            timer: 3000,
            position: 'bottom-end',
            showConfirmButton: false,
            toast: true,
            customClass: {
                popup: 'small-sweetalert'
            }
        });
    }
</script>

</body>
</html>