INSERT INTO article (id, title, outline, text) VALUES
(1, 'How to set up a WordPress site on an EC2 AWS instance with an SSL certificate',
'In this tutorial we will use AWS EC2, Load Balancer and Route53 together with Certbot',
'<h3>Step 1 – Get a domain name</h3>
<p>Access to you AWS management console and select the service Route53.</p>
<p>Type in your domain and click ‘Check’. Once you found the right available domain proceed adding to chart.<br>
Select the wanted option (at this point I opt for the version without www, which can be added later as an alias).
The domain registration at this point will be in process.</p>
<p>Once processed the request, you’ll be able to see your domain clicking on the option ‘Registered domains’ on the left-hand menu. We’ll come back here later to create the record sets.</p>
<h3>Step 2 – Create the EC2 instance</h3>
<p>
Select EC2 among the services in the console.
Select ‘Instances’ in the left-hand menu and ‘Launch Instance’ in the top-right corner of the window.<br>
Select Amazon MarketPlace from the options on the left and search for WordPress Certified by Bitnami and Automattic, once selected it will show you the pricing.<br>
It is possible to select an instance type free-tier eligible.<br>
At this point click ‘Review and Launch’.
It will prompt you for defining a key value pair, which we will need to SSH into our instance.<br>
You can pick up one already existing (make sure you downloaded it when you created it) or you can create and download a new one.<br>
After you downloaded the .pem file proceed clicking launch instance.<br>
Selecting the instance you can see now the public DNS of it, we will need it to SSH into it.
</p>
<h3>Step 3 – Request an SSL certificate from AWS </h3>
<p>
Select ‘Certificate Manager’ in the AWS console.<br>
Click ‘Request a certificate’.
Go on with the default options.<br>
Add the domain name(s) for which your are requesting it. <br>
At this point you’ll probably want to add the 2 version of your domain (with and without ‘www’).<br>
Select ‘Next’ and choose the type of validation.<br>
I go with the email validation, because it’s quicker, but in case you are not the admin of the account you’ll have to go with the DNS validation.<br>
Click ‘Review’ and confirm the request.
Select ‘Continue’.<br>
If you chose to validate through email you’ll receive an email for every domain you picked, click on the link in the email and click ‘I approve’.<br>
</p>
<h3>Step 4 – Create a load balancer and target group with associated certificate</h3>
<p>
After the certificate is approved we can proceed creating the load balancer .
Log into the EC2 dashboard.<br>
Select Load Balancer from the left-hand menu.
</p>
<p>
Click ‘Create load balancer’ and select ‘Application load balancer’.
Write a name for it.<br>
Add a listener with protocol HTTPS.
</p>
<p>
Select at least 2 availability zones.
Proceed clicking ‘Configure security settings’.<br>
Select your certificate from the dropdown list and leave the default options for the rest.
Click ‘Configure Security Groups’.<br>
Select the option ‘Create a new security group’.
You’ll need to add 2 rules with the following option selected:<br>
Type : HTTP<br>
Source: Anywhere<br>
Type : HTTPS<br>
Source: Anywhere<br>
</p>
<p>
Click ‘Configure routing’.
Give a name to the target group and click ‘Register target’.<br>
In the next window, select your instance at the bottom and click ‘Add to registered’.
Click ‘Review’ and then ‘Create’.<br> <br>
UPDATE: In order to redirect http to https, once your load balancer is active select it in the panel and select the tab ‘Listeners’.<br>
Select “View/edit rules”.
Click ‘Edit rules’, eliminate the default action and add the action ‘Redirect to..’, select ‘HTTPS’ on port 443 and update the rule.<br>
</p>
<h3>Step 5 –  Create the alias records</h3>
<p>
Let’s go back to Route 53.
Select your hosted zone and click ‘Add record set’.
</p>
<p>
One record will be without ‘www’, type A-iPv4. Check the option alias and select your load balancer from the dropdown list.
Repeat the same procedure for the domain with ‘www’.
</p>
<h3>Step 6 – SSH into the instance</h3>
<p>
At this point you instance is up and running with the domain name you chose. <br>
However to make the certificate effective we’ll need to modify the config file of our instance.<br>
To do so we will need the .pem file downloaded before.<br>
The following procedure works on linux and mac.<br>
Open a terminal.<br>
Type this to change the file permissions of the .pem file</p>
<code>chmod 600 /path-to-your-key.pem</code
<p>Then type this to ssh into the instance, using the DNS address you saw on the EC2 panel:</p>
<code>ssh bitnami@ec2-17-08-02-19.compute-1.amazonaws.com -i /path-to-your-key.pem</code>
<p>
Type in ‘yes’ if it asks you if you are sure you want to connect.<br>
Now we can see our application credentials.<br>
Type in cat ./bitnami_credentials. You will see something like:<br>
The default username and password is ''user'' and ''YUrahstyU'' .<br>
These are the credentials to access your wordpress dahsboard.<br>
Type in:
</p>
<code>vim /opt/bitnami/apps/wordpress/htdocs/wp-config.php</code>
<p>
Look for these lines:
</p>
<code>define(''WP_SITEURL'', ''http://'' . $_SERVER[''HTTP_HOST''] . ''/''); <br>
define(''WP_HOME'', ''http://'' . $_SERVER[''HTTP_HOST''] . ''/'');<br>
</code>
<p>
Modify them in this way:
</p>
<code>
define(''WP_SITEURL\'', ''https://'' . $_SERVER[''HTTP_HOST''] . ''/'');<br>
define(''WP_HOME'', ''https://'' . $_SERVER[''HTTP_HOST''] . ''/'');<br>
</code>
<p>
Before these 2 lines add this:
</p>
<code>
if (strpos($_SERVER[''HTTP_X_FORWARDED_PROTO''], ''https'') !== false)<br>
$_SERVER[''HTTPS\'']=''on'';
</code>
<p>
Save and close.<br>
Then let’s modify the server configurations. Type in:
</p>
<code>
vim /opt/bitnami/apps/wordpress/conf/httpd-prefix.conf.
</code>
<p>
Add this line at the top:
</p>
<code>
SetEnvIf x-forwarded-proto https HTTPS=on
</code>
<p>
Save and close<br>
Restart the server.<br>
</p>
<code>sudo /opt/bitnami/ctlscript.sh restart apache</code>
<p>
At this point the certificate will be effective, but you’ll need to wait to see the changes and if necessary clean the cache of your browser.
</p>
<p>
Update:<br>
In some cases there might be the need to manually change the ‘siteurl’ and ‘home’ values of our domain to the https version.<br>
In order to do so, connect via SSH to your instance. Before connecting to the database you can get your default password in the wp-config.php file.<br>
Type to access to your database:
</p>
<code>
sudo mysql -u bn_wordpress -p ''USE bitnami_wordpress; select option_name,option_value from wp_options WHERE option_name=''siteurl'' OR option_name=''home'';''
</code>
<p>
It will ask you for the password and it will show you your domain URL.<br>
If the url is not https you can change it this way:
</p>
<code>
sudo mysql -u bn_wordpress -p -e ''USE bitnami_wordpress; UPDATE wp_options SET option_value = ''https://127.0.0.1/wordpress/'' WHERE option_name = ''home'' OR option_name = ''siteurl'';''
</code>
<p>
Resources:
</p>'
);