INSERT INTO article_sections (article_id, sections) VALUES (1,
'{
"<h3>Step 1 – Get a domain name</h3>
<p>Route53 is the service to manage domain names and to route traffic to your resources.
Search for it on your AWS management console and search for the desired domain.</p>",
"<p>Once you found the right available domain proceed by adding to the chart.<br>
Select the wanted option (with or without www, the other version can be added later as an alias).
The domain registration at this point will be in process.</p>
<p>Once processed the request, you’ll be able to see your domain clicking on the option ‘Registered domains’ on the left-hand menu. We’ll come back here later to create the record sets.</p>",
"<h3>Step 2 – Create the EC2 instance</h3>
<p>EC2 is a service that allows you to deploy your applications
on scalable servers, so that you pay the resources you use.<br>Select EC2 among the services in the console.
Select ‘Instances’ in the left-hand menu and ‘Launch Instance’ in the top-right corner of the window.<br>
Select Amazon MarketPlace from the options on the left and search for WordPress Certified by Bitnami and Automattic,
and it will show you the pricing.<br>
In case you subscribed to the AWS within the past year, it will be possible to select a type of instance free-tier eligible.<br>
At this point click ‘Review and Launch’.
It will prompt you for defining a key-value pair, which we will need to SSH into our instance.<br>
You can pick up one already existing (make sure you downloaded it when you created it) or you can create and download a new one.<br>
After you downloaded the .pem file proceed clicking launch instance.<br>
By selecting the instance you can see now the public DNS of it, which we will need to it through SSH.
</p>",
"<h3>Step 3 – Create an Elastic IP</h3>
<p>Since the auto-assigned public IP changes every time you stop and start the instance, we need an elastic IP pointing at it.<br>
Search for ''Elastic IP'' on the EC2 dashboard. Allocate and elastic IP and associate it to the instance you just created.<br>
In this way your EC2 resource will maintain the same IP over time.<br>
</p>",
"<h3>Step 4 –  Create the alias records</h3>
<p>Let’s go back to Route 53.
Select your hosted zone and click ‘Add record set’.<br>
One record will be without ‘www’, type A-iPv4. Check the option alias and select your load balancer from the dropdown list.
Repeat the same procedure for the domain with ‘www’.</p>",
"<h3>Step 5 – SSH into the instance and install the SSL certificate</h3>
<p>In order to request and install the certificate it is possible to use the Bitnami HTTPS Configuration Tool<br>
To do so we will need the .pem file downloaded before.<br>
The following procedure works on linux and mac.<br>
Open a terminal.<br>
Type this to change the file permissions of the .pem file</p>
<br><br>
<code>chmod 600 /path-to-your-key.pem</code>
<br><br>
<p>Then type this to ssh into the instance, using the DNS address you saw on the EC2 panel:</p>
<br><br>
<code>ssh bitnami@ec2-17-08-02-19.compute-1.amazonaws.com -i /path-to-your-key.pem</code>
<br><br>
<p>Type in ‘yes’ if it asks you if you are sure you want to connect.<br>
Now we can see our application credentials.<br>
Type in cat ./bitnami_credentials. You will see something like:<br>
The default username and password is ''user'' and ''YUrahstyU'' .<br>
These are the credentials to access your Wordpress dahsboard.<br>
</p><p>Type in:</p><br><br>
<code>sudo /opt/bitnami/bncert-tool</code>
<br><br>
<p>
The wizard will guide you through the installation<br>
Once it is finished, restart the server.<br>
</p>
<br><br>
<code>sudo /opt/bitnami/ctlscript.sh restart apache</code>",
"<p>Update:<br>
In some cases there might be the need to manually change the ‘siteurl’ and ‘home’ values of our domain to the https version.<br>
In order to do so, connect via SSH to your instance. Before connecting to the database you can get your default password in the wp-config.php file.<br>
Type to access to your database:
</p>
<br><br>
<code>
sudo mysql -u bn_wordpress -p ''USE bitnami_wordpress; select option_name,option_value from wp_options WHERE option_name=''siteurl'' OR option_name=''home'';''
</code>
<br><br>
<p>
It will ask you for the password and it will show you your domain URL.<br>
If the url is not https you can change it this way:
</p>
<br><br>
<code>
sudo mysql -u bn_wordpress -p -e ''USE bitnami_wordpress; UPDATE wp_options SET option_value = ''https://127.0.0.1/wordpress/'' WHERE option_name = ''home'' OR option_name = ''siteurl'';''
</code><br><br><p>Resources:</p>"
}');







INSERT INTO article_resources (article_id, resources) VALUES (1,
'{"https://docs.bitnami.com/aws/how-to/generate-install-lets-encrypt-ssl/",
"https://docs.bitnami.com/aws/faq/get-started/find-credentials/#option-2-find-password-by-connecting-to-your-application-through-ssh",
"https://stackoverflow.com/questions/24603620/redirecting-ec2-elastic-load-balancer-from-http-to-https",
"https://stackoverflow.com/questions/46084104/ssl-cloudfront-ec2-wordpress-through-bitnami",
"http://coenraets.org/blog/2012/01/setting-up-wordpress-on-amazon-ec2-in-5-minutes/"
}');





https://aws.amazon.com/what-is-cloud-computing/#Types_of_cloud_computing