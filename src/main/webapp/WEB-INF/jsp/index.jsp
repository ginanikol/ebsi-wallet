<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/mainStyle.css">
    </head>
    <body>

    <div class="container">
        <div class="menu">
            <div class="menu-title">
                <img src="images\ihu_logo.png" alt="IHU Logo"> GN wallet</div>
            <div class="menu-item" onclick="showContent('keys')" id="keys-item">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                     stroke="currentColor" aria-hidden="true" class="h-6 w-6 shrink-0">
                    <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                        <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
                        <g id="SVGRepo_iconCarrier">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                  d="M15.6809 5.34814C14.0521 5.34814 12.7265 6.66395 12.7265 8.29353C12.7265 9.92311 14.0521 11.2389 15.6809 11.2389C17.3097 11.2389 18.6353 9.92311 18.6353 8.29353C18.6353 6.66395 17.3097 5.34814 15.6809 5.34814ZM14.2265 8.29353C14.2265 7.49816 14.8748 6.84814 15.6809 6.84814C16.487 6.84814 17.1353 7.49816 17.1353 8.29353C17.1353 9.0889 16.487 9.73891 15.6809 9.73891C14.8748 9.73891 14.2265 9.0889 14.2265 8.29353Z"
                                  fill="#1C274C"></path>
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                  d="M9.52998 20.8783C9.86298 20.414 9.97017 19.9429 9.96222 19.5233C10.3544 19.6387 10.7424 19.6533 11.1141 19.5828C11.8825 19.437 12.4511 18.9512 12.7527 18.5507L12.758 18.5437L12.7631 18.5366C13.2883 17.8043 13.2872 17.0543 13.1586 16.5164C13.0956 16.2528 13.0021 16.0361 12.9245 15.8846C12.8853 15.8081 12.849 15.746 12.8207 15.7005C12.8132 15.6885 12.8063 15.6775 12.7999 15.6677C12.7112 15.5021 12.6111 15.3719 12.5269 15.2737L12.5359 15.2647L13.0001 14.8024C13.3817 14.9849 13.7957 15.0999 14.1583 15.1749C14.744 15.2962 15.3171 15.3369 15.6807 15.3369C19.582 15.3369 22.75 12.1863 22.75 8.29344C22.75 4.40056 19.582 1.25 15.6807 1.25C11.7794 1.25 8.61144 4.40056 8.61144 8.29344C8.61144 9.2105 8.82018 9.99588 9.02588 10.549C9.07825 10.6898 9.13081 10.8166 9.18035 10.9279L1.92511 18.1535C1.66869 18.4089 1.36789 18.853 1.27697 19.4092C1.17837 20.0124 1.34031 20.6829 1.92511 21.2654L2.80687 22.1435C2.82046 22.1571 2.83457 22.1701 2.84916 22.1825C3.10385 22.3999 3.53164 22.6513 4.04572 22.7273C4.59712 22.8088 5.23527 22.6818 5.77579 22.1435L6.34232 21.5793C6.87523 21.8849 7.43853 21.9545 7.95941 21.8548C8.63497 21.7254 9.19686 21.321 9.51964 20.8924L9.5249 20.8854L9.52998 20.8783ZM10.1114 8.29344C10.1114 5.23477 12.602 2.75 15.6807 2.75C18.7594 2.75 21.25 5.23477 21.25 8.29344C21.25 11.3521 18.7594 13.8369 15.6807 13.8369C15.4075 13.8369 14.9372 13.8044 14.4623 13.7061C13.9654 13.6032 13.5752 13.4504 13.3674 13.2779C13.0699 13.031 12.6332 13.0508 12.3592 13.3237L11.4774 14.2019C11.2757 14.4028 11.0818 14.6305 10.9794 14.8933C10.8499 15.2261 10.8912 15.5463 11.0394 15.8121C11.1273 15.9697 11.2689 16.1202 11.3278 16.183L11.3476 16.2042C11.4173 16.2811 11.4555 16.3314 11.4834 16.387L11.5098 16.4397L11.54 16.4817L11.5468 16.4924C11.5558 16.507 11.5712 16.533 11.5895 16.5685C11.6267 16.6412 11.6709 16.7445 11.6997 16.8652C11.7544 17.0937 11.7538 17.3656 11.5494 17.6551C11.4087 17.8384 11.1424 18.0506 10.8345 18.1091C10.5769 18.1579 10.1571 18.1261 9.59673 17.5681C9.30409 17.2766 8.83089 17.2766 8.53825 17.5681L8.24433 17.8608C7.96748 18.1365 7.94891 18.5782 8.20054 18.8761C8.20194 18.8778 8.2058 18.8826 8.2116 18.8903C8.22363 18.9062 8.24339 18.9336 8.2668 18.9704C8.31483 19.0461 8.37128 19.1508 8.41138 19.2706C8.48694 19.4963 8.49882 19.7374 8.31639 19.9966C8.19643 20.1519 7.95303 20.3287 7.67726 20.3815C7.4429 20.4264 7.14284 20.3931 6.8045 20.0562C6.51186 19.7647 6.03866 19.7647 5.74602 20.0562L4.7173 21.0807C4.55241 21.2449 4.4068 21.2643 4.26505 21.2434C4.09729 21.2186 3.93333 21.1293 3.84077 21.0562L2.9836 20.2025C2.74543 19.9653 2.73591 19.7821 2.75733 19.6511C2.78643 19.4731 2.89711 19.3025 2.9836 19.2163L10.6279 11.6033C10.8747 11.3575 10.9185 10.9735 10.7333 10.6784L10.7311 10.6748C10.7284 10.6703 10.7232 10.6615 10.7158 10.6487C10.7012 10.6231 10.6781 10.5814 10.6494 10.5251C10.5918 10.4123 10.5122 10.2423 10.4318 10.0262C10.2701 9.59135 10.1114 8.98632 10.1114 8.29344ZM8.20054 18.8761C8.20192 18.8777 8.2033 18.8793 8.20469 18.881L8.20354 18.8796L8.20054 18.8761Z"
                                  fill="#1C274C"></path>
                        </g>
                    </svg>
                </svg>
                Keys
            </div>
            <div class="menu-item" onclick="showContent('dids')" id="dids-item">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                     stroke="currentColor" aria-hidden="true" class="h-6 w-6 shrink-0">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" aria-hidden="true" class="h-6 w-6 shrink-0">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M7.864 4.243A7.5 7.5 0 0119.5 10.5c0 2.92-.556 5.709-1.568 8.268M5.742 6.364A7.465 7.465 0 004.5 10.5a7.464 7.464 0 01-1.15 3.993m1.989 3.559A11.209 11.209 0 008.25 10.5a3.75 3.75 0 117.5 0c0 .527-.021 1.049-.064 1.565M12 10.5a14.94 14.94 0 01-3.6 9.75m6.633-4.596a18.666 18.666 0 01-2.485 5.33"></path>
                    </svg>
                </svg>
                DIDs
            </div>
            <div class="menu-item" onclick="showContent('VC')" id="VC-item">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                     stroke="currentColor" aria-hidden="true" class="h-6 w-6 shrink-0">
                    <path stroke-linecap="round" stroke-linejoin="round"
                          d="M15 9h3.75M15 12h3.75M15 15h3.75M4.5 19.5h15a2.25 2.25 0 002.25-2.25V6.75A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25v10.5A2.25 2.25 0 004.5 19.5zm6-10.125a1.875 1.875 0 11-3.75 0 1.875 1.875 0 013.75 0zm1.294 6.336a6.721 6.721 0 01-3.17.789 6.721 6.721 0 01-3.168-.789 3.376 3.376 0 016.338 0z"/>
                </svg>
                VC
            </div>
            <div class="menu-item menu-logout" id="logout">
                <svg viewBox="0 -0.5 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                    <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g>
                    <g id="SVGRepo_iconCarrier">
                        <path d="M11.75 9.874C11.75 10.2882 12.0858 10.624 12.5 10.624C12.9142 10.624 13.25 10.2882 13.25 9.874H11.75ZM13.25 4C13.25 3.58579 12.9142 3.25 12.5 3.25C12.0858 3.25 11.75 3.58579 11.75 4H13.25ZM9.81082 6.66156C10.1878 6.48991 10.3542 6.04515 10.1826 5.66818C10.0109 5.29121 9.56615 5.12478 9.18918 5.29644L9.81082 6.66156ZM5.5 12.16L4.7499 12.1561L4.75005 12.1687L5.5 12.16ZM12.5 19L12.5086 18.25C12.5029 18.25 12.4971 18.25 12.4914 18.25L12.5 19ZM19.5 12.16L20.2501 12.1687L20.25 12.1561L19.5 12.16ZM15.8108 5.29644C15.4338 5.12478 14.9891 5.29121 14.8174 5.66818C14.6458 6.04515 14.8122 6.48991 15.1892 6.66156L15.8108 5.29644ZM13.25 9.874V4H11.75V9.874H13.25ZM9.18918 5.29644C6.49843 6.52171 4.7655 9.19951 4.75001 12.1561L6.24999 12.1639C6.26242 9.79237 7.65246 7.6444 9.81082 6.66156L9.18918 5.29644ZM4.75005 12.1687C4.79935 16.4046 8.27278 19.7986 12.5086 19.75L12.4914 18.25C9.08384 18.2892 6.28961 15.5588 6.24995 12.1513L4.75005 12.1687ZM12.4914 19.75C16.7272 19.7986 20.2007 16.4046 20.2499 12.1687L18.7501 12.1513C18.7104 15.5588 15.9162 18.2892 12.5086 18.25L12.4914 19.75ZM20.25 12.1561C20.2345 9.19951 18.5016 6.52171 15.8108 5.29644L15.1892 6.66156C17.3475 7.6444 18.7376 9.79237 18.75 12.1639L20.25 12.1561Z"
                              fill="#ffffff"></path>
                    </g>
                </svg>
                <div class="menu-item-content">
                    <a href="logout">Logout</a>
                </div>
            </div>
        </div>

        <div class="content">
            <div id="keys-content" class="hidden">
            </div>
            <div id="dids-content" class="hidden">

            </div>
            <div id="VC-content" class="hidden">
                Information about the VC. This is the content for the VC.
            </div>

        </div>

        <button id="createKeyButton" class="hidden">Create a New Key</button>
        <button id="createDIDButton" class="hidden">Create a DID</button>


    </div>


    <script>
        function showContent(item) {
            const contentSections = document.querySelectorAll('.content > div');
            contentSections.forEach(function (section) {
                section.classList.add('hidden');
            });

            const menuItems = document.querySelectorAll('.menu-item');
            menuItems.forEach(function (menuItem) {
                menuItem.classList.remove('selected');
            });

            const selectedContent = document.getElementById(item + '-content');
            selectedContent.classList.remove('hidden');

            const selectedItem = document.getElementById(item + '-item');
            selectedItem.classList.add('selected');

            const createKeyButton = document.getElementById('createKeyButton');
            if (item === 'keys') {
                createKeyButton.classList.remove('hidden');
            } else {
                createKeyButton.classList.add('hidden');
            }

            const createDIDButton = document.getElementById('createDIDButton');
            if (item === 'dids') {
                createDIDButton.classList.remove('hidden');
            } else {
                createDIDButton.classList.add('hidden');
            }

            if (item === 'keys') {
                fetch('keys/all')
                    .then(response => response.text())
                    .then(data => {
                        // Update the "Keys" content section with the loaded data
                        document.getElementById('keys-content').innerHTML = data;
                    })
                    .catch(error => console.error('Error:', error));
            }

            if (item === 'dids') {
                fetch('dids/all')
                    .then(response => response.text())
                    .then(data => {
                        document.getElementById('dids-content').innerHTML = data;
                    })
                    .catch(error => console.error('Error:', error));
            }
        }

            document.getElementById("createKeyButton").addEventListener("click", function() {
                // Make an AJAX request to create a new key
                fetch('keys/create', {
                    method: 'GET', // Use GET request
                })
                    .then(() => {
                        // On success, fetch the list of all keys again and update the HTML
                        if (document.getElementById('keys-item').classList.contains('selected')) {
                            fetch('keys/all')
                                .then(response => response.text())
                                .then(data => {
                                    // Update the "Keys" content section with the loaded data
                                    document.getElementById('keys-content').innerHTML = data;
                                })
                                .catch(error => console.error('Error:', error));
                        }
                    })
                    .catch(error => console.error('Error:', error));
            });

            document.getElementById("createDIDButton").addEventListener("click", function() {
                // Make an AJAX request to create a new did
                fetch('dids/create', {
                    method: 'GET',
                })
                    .then(() => {
                        // On success, fetch the list of all dids again and update the HTML
                        if (document.getElementById('dids-item').classList.contains('selected')) {
                            fetch('dids/all')
                                .then(response => response.text())
                                .then(data => {
                                    // Update the "DIDs" content section with the loaded data
                                    document.getElementById('dids-content').innerHTML = data;
                                })
                                .catch(error => console.error('Error:', error));
                        }
                    })
                    .catch(error => console.error('Error:', error));
            });
    </script>

    </body>
</html>
