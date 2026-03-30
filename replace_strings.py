import os
import glob

replacements = {
    '"QuickServe"': 'stringResource(R.string.app_name)',
    '"Phone Number"': 'stringResource(R.string.phone_number)',
    '"Login As:"': 'stringResource(R.string.login_as)',
    '"Worker"': 'stringResource(R.string.worker)',
    '"Client"': 'stringResource(R.string.client)',
    '"Enter OTP"': 'stringResource(R.string.enter_otp)',
    '"Verify OTP"': 'stringResource(R.string.verify_otp)',
    '"Send OTP"': 'stringResource(R.string.send_otp)',
    '"Don\'t have an account? Register"': 'stringResource(R.string.dont_have_account_register)',
    '"Role Selection"': 'stringResource(R.string.role_selection)',
    '"Choose your role to register:"': 'stringResource(R.string.choose_your_role_to_register)',
    '"Register as Client"': 'stringResource(R.string.register_as_client)',
    '"Register as Worker"': 'stringResource(R.string.register_as_worker)',
    '"Client Registration"': 'stringResource(R.string.client_registration)',
    '"App Language"': 'stringResource(R.string.app_language)',
    '"Full Name"': 'stringResource(R.string.full_name)',
    '"City"': 'stringResource(R.string.city)',
    '"Enter Phone OTP"': 'stringResource(R.string.enter_phone_otp)',
    '"Verify Phone OTP"': 'stringResource(R.string.verify_phone_otp)',
    '"Send Phone OTP"': 'stringResource(R.string.send_phone_otp)',
    '"Phone Verified ✓"': 'stringResource(R.string.phone_verified)',
    '"Aadhaar or PAN Card"': 'stringResource(R.string.aadhaar_or_pan_card)',
    '"Enter ID OTP"': 'stringResource(R.string.enter_id_otp)',
    '"Verify ID OTP"': 'stringResource(R.string.verify_id_otp)',
    '"Send ID OTP"': 'stringResource(R.string.send_id_otp)',
    '"ID Verified ✓"': 'stringResource(R.string.id_verified)',
    '"Register"': 'stringResource(R.string.register)',
    '"Worker Registration"': 'stringResource(R.string.worker_registration)',
    '"Select Services (One or more)"': 'stringResource(R.string.select_services)',
    '"Available Workers"': 'stringResource(R.string.available_workers)',
    '"Give Rating & Review"': 'stringResource(R.string.give_rating_review)',
    '"Review ${selectedWorker?.name}"': 'stringResource(R.string.review) + " ${selectedWorker?.name}"',
    '"Write your review"': 'stringResource(R.string.write_your_review)',
    '"Submit"': 'stringResource(R.string.submit)',
    '"Cancel"': 'stringResource(R.string.cancel)',
    '"Home"': 'stringResource(R.string.home)',
    '"Bookings"': 'stringResource(R.string.bookings)',
    '"Profile"': 'stringResource(R.string.profile)',
    '"My Bookings"': 'stringResource(R.string.my_bookings)',
    '"You have no active bookings."': 'stringResource(R.string.no_active_bookings)',
    '"Logout"': 'stringResource(R.string.logout)',
    '"Available Jobs"': 'stringResource(R.string.available_jobs)',
    '"No jobs available right now."': 'stringResource(R.string.no_jobs_available)',
    '"Dashboard"': 'stringResource(R.string.dashboard)',
    '"Completed Jobs: 0"': 'stringResource(R.string.completed_jobs) + ": 0"',
    '"Pending Jobs: 0"': 'stringResource(R.string.pending_jobs) + ": 0"',
    '"Total Earnings: \\u20B90"': 'stringResource(R.string.total_earnings) + ": \\u20B90"',
    '"Service: ${worker.service}"': 'stringResource(R.string.service) + ": ${worker.service}"'
}

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    original_content = content

    # Add imports if they don't exist
    if 'import androidx.compose.ui.res.stringResource' not in content:
        # Find the last import
        lines = content.split('\n')
        last_import_idx = -1
        for i, line in enumerate(lines):
            if line.startswith('import '):
                last_import_idx = i
        
        if last_import_idx != -1:
            lines.insert(last_import_idx + 1, 'import androidx.compose.ui.res.stringResource')
            lines.insert(last_import_idx + 2, 'import com.quickserve.app.R')
            content = '\n'.join(lines)

    for old, new in replacements.items():
        # Replace occurrences like Text("...")
        content = content.replace(f'Text({old})', f'Text({new})')
        content = content.replace(f'Text(text = {old}', f'Text(text = {new}')
        content = content.replace(f'Label = {{ Text({old}) }}', f'Label = {{ Text({new}) }}')
        content = content.replace(f'label = {{ Text({old}) }}', f'label = {{ Text({new}) }}')
        content = content.replace(f'title = {{ Text({old}) }}', f'title = {{ Text({new}) }}')
        
    if content != original_content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Updated {filepath}")

# Process all .kt files in ui/screens
pattern = "app/src/main/java/com/quickserve/app/ui/screens/**/*.kt"
for filepath in glob.glob(pattern, recursive=True):
    process_file(filepath)

print("Done replacing.")
