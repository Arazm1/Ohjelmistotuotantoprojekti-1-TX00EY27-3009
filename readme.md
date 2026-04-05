## Fuel Tracker App
A simple application to calculate fuel consumption and total trip cost. Supports multiple languages by a database-driven localization system.

<hr>

### Setup Instructions

### 1. Database setup
**Requirements**
* MariaDB or MySQL

**Step 1**
```sql
CREATE DATABASE sep2_week3hw;
USE sep2_week3hw;
```

**Step 2**
Execute the provided SQL script: [script](https://github.com/Arazm1/ohjelmistotuotantoprojekti-1-tx00ey27-3009/blob/Week_11HW/script.sql).




### 2. Insert Data into DB Tables:
After creating successfully the Database and tables, insert multilingual labels using the following SQL script: [Localization data](https://github.com/Arazm1/ohjelmistotuotantoprojekti-1-tx00ey27-3009/blob/Week_11HW/localization-data.sql).



<hr>

### 3. Database Configuration
Your application needs to connect to the database using environmental-specific credentials.

A .env-sample file is provided in the project.
Create a new .env file in the root and copy the structure from .env-sample.
Ensure that the .env is in the .gitignore file to prevent your credentials being leaked.

Then replace the placeholder values with your own database configuration:

At the end, the .env file should look something like this with your own values:

```.env
DB_URL="jdbc:mysql://localhost:3306/sep2_week3hw"
DB_USERNAME=your_user
DB_PASSWORD=your_password
```

<hr>

### 4. Running the Application
Run the Main.java from your preferred IDE.



<!--
**ENGLISH (en)**
```sql
INSERT INTO localization_strings (`key`, `value`, `language`) VALUES
('app.title',          'Fuel Consumption and Total Cost Calculator', 'en'),
('distance.label',     'Distance (km):', 'en'),
('consumption.label',  'Fuel Consumption (L/100 km):', 'en'),
('price.label',        'Fuel Price (per liter):', 'en'),
('calculate.button',   'Calculate total fuel and cost', 'en'),
('result1.label',      'Total Fuel: %.2f L', 'en'),
('result2.label',      'Total Cost: %.2f', 'en'),
('invalid.input',      'Please enter valid numbers', 'en'),
('current_time',       'Current Time: %s', 'en'),
('time_format',        'HH:mm:ss', 'en'),
('distance.prompt',    'Enter distance', 'en'),
('consumption.prompt', 'Enter fuel consumption', 'en'),
('price.prompt',       'Enter fuel price', 'en');
```

**FRENCH (fr)**
```sql
INSERT INTO localization_strings (`key`, `value`, `language`) VALUES
('app.title',          'Calculateur de consommation de carburant et coût total', 'fr'),
('distance.label',     'Distance (km):', 'fr'),
('consumption.label',  'Consommation de carburant (L/100 km):', 'fr'),
('price.label',        'Prix du carburant (par litre)', 'fr'),
('calculate.button',   'Calculer le carburant total et le coût', 'fr'),
('result1.label',      'Carburant total: %.2f L', 'fr'),
('result2.label',      'Coût total: %.2f', 'fr'),
('invalid.input',      'Veuillez entrer des nombres valides', 'fr'),
('current_time',       'Heure actuelle: %s', 'fr'),
('time_format',        'HH:mm:ss', 'fr'),
('distance.prompt',    'Entrez la distance', 'fr'),
('consumption.prompt', 'Entrez la consommation de carburant', 'fr'),
('price.prompt',       'Entrez le prix du carburant', 'fr');
```

**JAPANESE (ja)**
```sql
INSERT INTO localization_strings (`key`, `value`, `language`) VALUES
('app.title',          '燃料消費量と総費用計算機', 'ja'),
('distance.label',     '距離 (km):', 'ja'),
('consumption.label',  '燃料消費量 (L/100 km):', 'ja'),
('price.label',        '燃料価格 (1リットルあたり):', 'ja'),
('calculate.button',   '燃料と費用を計算する', 'ja'),
('result1.label',      '総燃料: %.2f L', 'ja'),
('result2.label',      '総費用: %.2f', 'ja'),
('invalid.input',      '有効な数字を入力してください', 'ja'),
('current_time',       '現在時刻: %s', 'ja'),
('time_format',        'HH:mm:ss', 'ja'),
('distance.prompt',    '距離を入力してください', 'ja'),
('consumption.prompt', '燃料消費量を入力してください', 'ja'),
('price.prompt',       '燃料価格を入力してください', 'ja');
```

**PERSIAN (fa)**
```sql
INSERT INTO localization_strings (`key`, `value`, `language`) VALUES
('app.title',          'ماشین‌حساب مصرف سوخت و هزینه کل', 'fa'),
('distance.label',     'مسافت (کیلومتر):', 'fa'),
('consumption.label',  'مصرف سوخت (لیتر/۱۰۰ کیلومتر):', 'fa'),
('price.label',        'قیمت سوخت (به ازای هر لیتر):', 'fa'),
('calculate.button',   'محاسبه سوخت و هزینه کل', 'fa'),
('result1.label',      'کل سوخت: %.2f لیتر', 'fa'),
('result2.label',      'هزینه کل: %.2f', 'fa'),
('invalid.input',      'لطفاً اعداد معتبر وارد کنید', 'fa'),
('current_time',       'زمان فعلی: %s', 'fa'),
('time_format',        'HH:mm:ss', 'fa'),
('distance.prompt',    'مسافت را وارد کنید', 'fa'),
('consumption.prompt', 'مصرف سوخت را وارد کنید', 'fa'),
('price.prompt',       'قیمت سوخت را وارد کنید', 'fa');
```
-->


