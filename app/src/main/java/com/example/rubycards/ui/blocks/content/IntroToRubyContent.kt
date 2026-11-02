package com.example.rubycards.ui.blocks.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IntroToRubyContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // === РАЗДЕЛ 1: Что такое Ruby? ===
        SectionTitle("Что такое Ruby?")
        BodyText("Ruby — это динамический, объектно-ориентированный язык программирования общего назначения. Он известен своим элегантным синтаксисом, который легко читать и писать.")
        Spacer(modifier = Modifier.height(12.dp))
        BodyText("Ruby был создан для того, чтобы сделать программирование приятным для разработчиков. Его часто называют «языком, который делает программистов счастливыми».")
        Spacer(modifier = Modifier.height(16.dp))

        CodeBlock("""# Простейшая программа на Ruby
puts "Привет, мир!"

# Интерполяция строк
name = "Ruby"
puts "Я изучаю #{name}!"

# Математика
puts 2 + 2        # => 4
puts 10 / 3       # => 3 (целочисленное деление)
puts 10.0 / 3     # => 3.333...""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 2: История создания ===
        SectionTitle("История создания")
        BodyText("Ruby был создан Юкихиро Мацумото (известным как Matz) в Японии в 1993 году. Первая публичная версия вышла в 1995 году.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Matz хотел объединить лучшее из разных языков:")
        Spacer(modifier = Modifier.height(4.dp))

        FeatureItem("Perl", "мощная работа с текстом")
        FeatureItem("Python", "простота и читаемость")
        FeatureItem("Smalltalk", "чистая объектная модель")
        FeatureItem("Lisp", "функциональные возможности")
        FeatureItem("Eiffel", "проектирование по контракту")

        Spacer(modifier = Modifier.height(12.dp))
        BodyText("В 2004 году появился фреймворк Ruby on Rails, который совершил революцию в веб-разработке и принёс Ruby мировую популярность.")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 3: Философия Ruby ===
        SectionTitle("Философия Ruby")
        BodyText("Главный принцип Ruby — MINASWAN (Matz Is Nice And So We Are Nice). Это означает дружелюбное сообщество и уважение к разработчикам.")
        Spacer(modifier = Modifier.height(8.dp))

        BodyText("Ключевые принципы:")
        Spacer(modifier = Modifier.height(4.dp))
        FeatureItem("Принцип наименьшего удивления", "Код должен вести себя интуитивно")
        FeatureItem("Программисты — люди", "Язык должен служить людям, а не машинам")
        FeatureItem("Естественность", "Синтаксис близок к английскому языку")
        FeatureItem("Гибкость", "Множество способов решить одну задачу")

        Spacer(modifier = Modifier.height(12.dp))
        QuoteBlock(
            "Я надеюсь, что Ruby поможет каждому программисту в мире быть продуктивным, получать удовольствие от программирования и быть счастливым.",
            "— Юкихиро Мацумото"
        )
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 4: Установка Ruby ===
        SectionTitle("Установка Ruby")
        BodyText("Прежде чем начать, нужно установить Ruby на компьютер:")
        Spacer(modifier = Modifier.height(8.dp))

        BodyText("На macOS (Ruby уже предустановлен, но лучше обновить):")
        CodeBlock("""# Через Homebrew
brew install ruby

# Или через rbenv (рекомендуется)
brew install rbenv
rbenv install 3.2.0
rbenv global 3.2.0""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("На Windows:")
        CodeBlock("""# Скачайте RubyInstaller с сайта:
# https://rubyinstaller.org/
# Запустите установщик и следуйте инструкциям""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("На Linux (Ubuntu/Debian):")
        CodeBlock("""sudo apt update
sudo apt install ruby-full

# Проверка установки
ruby -v   # => ruby 3.2.0...""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 5: Первая программа ===
        SectionTitle("Первая программа")
        BodyText("Создайте файл hello.rb и напишите:")
        Spacer(modifier = Modifier.height(8.dp))

        CodeBlock("""# hello.rb
puts "Привет, мир!"

# Запуск в терминале:
# ruby hello.rb""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("Или запустите интерактивную консоль IRB (Interactive Ruby):")
        CodeBlock("""# В терминале введите:
irb

# Теперь можно писать код напрямую:
irb> 2 + 2
=> 4
irb> "Hello".upcase
=> "HELLO"
irb> exit   # выход из IRB""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 6: Комментарии ===
        SectionTitle("Комментарии в коде")
        BodyText("Комментарии помогают объяснять код. Ruby игнорирует их при выполнении.")
        Spacer(modifier = Modifier.height(8.dp))

        CodeBlock("""# Это однострочный комментарий

=begin
Это многострочный комментарий.
Он может занимать несколько строк.
=end

# Документирующие комментарии (YARD):
# @param name [String] имя пользователя
# @return [String] приветствие
def greet(name)
  "Привет, #{name}!"
end""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 7: Переменные и типы данных ===
        SectionTitle("Переменные и типы данных")
        BodyText("В Ruby не нужно объявлять тип переменной — он определяется автоматически. Ruby — язык с динамической типизацией.")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("Основные типы данных:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""# String (строка)
name = "Alice"
greeting = 'Привет'

# Integer (целое число)
age = 25
year = 2024

# Float (дробное число)
price = 19.99
pi = 3.14159

# Boolean (логический тип)
is_active = true
is_deleted = false

# Symbol (символ — лёгкая строка-идентификатор)
status = :active
color = :red

# nil (отсутствие значения)
nothing = nil

# Array (массив)
fruits = ["яблоко", "банан", "апельсин"]
numbers = [1, 2, 3, 4, 5]

# Hash (хэш — словарь/ассоциативный массив)
user = {
  name: "Alice",
  age: 25,
  email: "alice@example.com"
}""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("Важно: в Ruby всё является объектом. Даже числа и nil имеют методы:")
        Spacer(modifier = Modifier.height(8.dp))

        CodeBlock("""25.class          # => Integer
"Hello".length    # => 5
nil.class         # => NilClass
true.to_s         # => "true"
5.times { puts "Ruby!" }  # напечатает 5 раз""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 8: Операторы ===
        SectionTitle("Операторы")
        BodyText("Арифметические операторы:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""+  -  *  /     # сложение, вычитание, умножение, деление
%              # остаток от деления (10 % 3 => 1)
**             # возведение в степень (2 ** 3 => 8)""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("Операторы сравнения:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""==   # равно         (5 == 5   => true)
!=   # не равно      (5 != 3   => true)
>    # больше        (5 > 3    => true)
<    # меньше        (3 < 5    => true)
>=   # больше или равно
<=   # меньше или равно
<=>  # «космический корабль» (-1, 0 или 1)""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("Логические операторы:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""&&   # И             (true && false => false)
||   # ИЛИ           (true || false => true)
!    # НЕ            (!true => false)

# Также можно использовать слова:
and, or, not""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 9: Условные конструкции ===
        SectionTitle("Условные конструкции")
        BodyText("Условия позволяют выполнять код только при определённых обстоятельствах:")
        Spacer(modifier = Modifier.height(8.dp))

        CodeBlock("""age = 18

# Базовый if/else
if age >= 18
  puts "Совершеннолетний"
else
  puts "Несовершеннолетний"
end

# elsif (множественные условия)
score = 85

if score >= 90
  puts "Отлично!"
elsif score >= 70
  puts "Хорошо"
elsif score >= 50
  puts "Удовлетворительно"
else
  puts "Не сдано"
end

# Модификатор if (короткая запись)
puts "Взрослый" if age >= 18

# unless (обратный if)
puts "Ребёнок" unless age >= 18

# case/when (аналог switch)
day = "понедельник"

case day
when "понедельник"
  puts "Начало недели"
when "пятница"
  puts "Почти выходные!"
when "суббота", "воскресенье"
  puts "Выходной!"
else
  puts "Обычный день"
end""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 10: Циклы ===
        SectionTitle("Циклы")
        BodyText("Циклы позволяют повторять действия многократно:")
        Spacer(modifier = Modifier.height(8.dp))

        CodeBlock("""# Цикл times (самый идиоматичный)
3.times { puts "Ruby!" }
# => Ruby! Ruby! Ruby!

# Цикл each (для коллекций)
fruits = ["яблоко", "банан", "апельсин"]
fruits.each do |fruit|
  puts "Я люблю #{fruit}"
end

# Цикл while
count = 0
while count < 5
  puts count
  count += 1
end

# Цикл until (обратный while)
count = 0
until count >= 5
  puts count
  count += 1
end

# Цикл for (редко используется)
for i in 1..5
  puts i
end

# Методы map и select (функциональный стиль)
numbers = [1, 2, 3, 4, 5]

doubled = numbers.map { |n| n * 2 }
# => [2, 4, 6, 8, 10]

evens = numbers.select { |n| n.even? }
# => [2, 4]""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 11: Методы ===
        SectionTitle("Методы (функции)")
        BodyText("Методы — это именованные блоки кода, которые можно вызывать многократно:")
        Spacer(modifier = Modifier.height(8.dp))

        CodeBlock("""# Определение метода
def greet(name)
  "Привет, #{name}!"
end

# Вызов метода
puts greet("Alice")   # => Привет, Alice!

# Метод с параметром по умолчанию
def greet(name = "мир")
  "Привет, #{name}!"
end

greet()        # => Привет, мир!
greet("Bob")   # => Привет, Bob!

# Метод с несколькими параметрами
def add(a, b)
  a + b
end

puts add(3, 5)   # => 8

# Возврат значения (последнее выражение)
def square(x)
  x * x    # не нужен return!
end

# Явный return (обычно не нужен)
def absolute(x)
  return -x if x < 0
  x
end

# Метод с блоком
def repeat(n)
  n.times { yield }
end

repeat(3) { puts "Hello!" }""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 12: ООП ===
        SectionTitle("Объектно-ориентированное программирование")
        BodyText("Ruby — полностью объектно-ориентированный язык. Всё в Ruby — объект, включая числа, строки и даже nil.")
        Spacer(modifier = Modifier.height(8.dp))

        BodyText("Классы и объекты:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""# Определение класса
class Dog
  # Конструктор
  def initialize(name, breed)
    @name = name      # переменная экземпляра
    @breed = breed
  end

  # Метод экземпляра
  def bark
    "#{@name} говорит: Гав!"
  end

  # Геттер
  def name
    @name
  end
end

# Создание объекта
dog = Dog.new("Бобик", "Лабрадор")
puts dog.bark   # => Бобик говорит: Гав!""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("Наследование:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""class GuideDog < Dog   # наследуется от Dog
  def guide
    "#{@name} ведёт хозяина"
  end
end

guide = GuideDog.new("Рекс", "Овчарка")
puts guide.bark    # унаследованный метод
puts guide.guide   # собственный метод""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 13: Обработка ошибок ===
        SectionTitle("Обработка ошибок")
        BodyText("Ruby предоставляет механизмы для обработки исключений:")
        Spacer(modifier = Modifier.height(8.dp))

        CodeBlock("""begin
  result = 10 / 0
rescue ZeroDivisionError => e
  puts "Ошибка: #{e.message}"
  # => Ошибка: divided by 0
rescue StandardError => e
  puts "Другая ошибка: #{e.message}"
ensure
  puts "Этот код выполнится всегда"
end

# Короткая форма
result = 10 / 0 rescue 0   # => 0

# Создание собственных исключений
class MyError < StandardError
end

raise MyError, "Что-то пошло не так" """)
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 14: Полезные советы ===
        SectionTitle("Полезные советы для начинающих")
        Spacer(modifier = Modifier.height(4.dp))

        TipItem("Используйте 2 пробела для отступов", "Не табуляцию — это стандарт Ruby")
        TipItem("Именуйте переменные в snake_case", "my_variable, а не myVariable")
        TipItem("Именуйте классы в CamelCase", "MyClass, а не my_class")
        TipItem("Избегайте лишних скобок", "puts \"Hello\" вместо puts(\"Hello\")")
        TipItem("Используйте символы вместо строк для ключей хэша", "{ name: \"Alice\" } вместо { \"name\" => \"Alice\" }")
        TipItem("Читайте документацию", "ri — встроенная справка: ri String.split")
        TipItem("Используйте IRB для экспериментов", "Быстрая проверка идей без создания файлов")
        TipItem("Пишите тесты", "RSpec — самый популярный фреймворк для тестирования")

        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 15: Полезные материалы ===
        SectionTitle("Полезные материалы")
        Spacer(modifier = Modifier.height(4.dp))

        LinkItem("Официальный сайт Ruby", "https://www.ruby-lang.org/ru/")
        LinkItem("Документация Ruby", "https://ruby-doc.org/")
        LinkItem("Ruby Style Guide", "https://rubystyle.guide/")
        LinkItem("Try Ruby (интерактивный курс)", "https://try.ruby-lang.org/")
        LinkItem("Ruby Koans (обучение через тесты)", "http://rubykoans.com/")
        LinkItem("Codecademy: Learn Ruby", "https://www.codecademy.com/learn/learn-ruby")
        LinkItem("Книга «Programming Ruby»", "https://pragprog.com/titles/ruby5/")

        Spacer(modifier = Modifier.height(32.dp))

        // Индикатор завершения
        CompletionBanner()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// === Вспомогательные компоненты ===

@Composable
private fun SectionTitle(title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE53935),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        // Разделитель вместо HorizontalDivider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFFE53935))
        )
    }
}

@Composable
private fun BodyText(text: String) {
    // Используем обычный Text вместо Row — это решает проблему с переносом
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = Color(0xFF333333),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CodeBlock(code: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF1E1E1E),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = code,
            color = Color(0xFFD4D4D4),
            fontFamily = FontFamily.Monospace,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
private fun FeatureItem(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(
            text = "◆ ",
            color = Color(0xFFE53935),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 15.sp
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF666666)
            )
        }
    }
}

@Composable
private fun TipItem(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFFF9E6))
            .padding(12.dp)
    ) {
        Text(
            text = "💡 ",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF666666)
            )
        }
    }
}

@Composable
private fun QuoteBlock(text: String, author: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFF5F5F5),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "«$text»",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF333333),
                fontStyle = FontStyle.Italic,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = author,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFE53935),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun LinkItem(title: String, url: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { /* TODO: открыть ссылку */ }
    ) {
        Text(
            text = "🔗 $title",
            color = Color(0xFFE53935),
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
        Text(
            text = url,
            color = Color(0xFF888888),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 28.dp, top = 2.dp)
        )
    }
}

@Composable
private fun CompletionBanner() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFE53935),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🎉 Теория пройдена!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Теперь вкладка «Практика» стала доступна",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}