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
fun IntroToRubyContent(scrollState: androidx.compose.foundation.ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // === РАЗДЕЛ 1: Что такое Ruby? ===
        SectionTitle("Что такое Ruby?")
        BodyText("Ruby — это динамический, объектно-ориентированный язык программирования общего назначения. Он известен своим элегантным синтаксисом, который легко читать и писать.")
        Spacer(modifier = Modifier.height(12.dp))
        BodyText("Ruby был создан для того, чтобы сделать программирование приятным для разработчиков. Его часто называют «языком, который делает программистов счастливыми».")
        Spacer(modifier = Modifier.height(16.dp))

        ExplanationBlock(
            "Как работает код?",
            "Когда вы пишете код на Ruby, компьютер читает его строка за строкой и выполняет команды. Например, команда `puts` выводит текст на экран."
        )
        CodeBlock("""# Простейшая программа на Ruby
# puts - это команда "вывести на экран"
puts "Привет, мир!"

# Можно использовать переменные
name = "Ruby"
puts "Я изучаю #{name}!"  # #{name} подставит значение переменной

# Ruby может считать
puts 2 + 2        # Выведет: 4
puts 10 / 3       # Выведет: 3 (целочисленное деление)
puts 10.0 / 3     # Выведет: 3.333... (дробное деление)""")
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
        ExplanationBlock(
            "Что такое Homebrew?",
            "Homebrew — это менеджер пакетов для macOS. Он позволяет легко устанавливать программы через терминал."
        )
        CodeBlock("""# Через Homebrew
brew install ruby

# Или через rbenv (рекомендуется для управления версиями)
brew install rbenv
rbenv install 3.2.0
rbenv global 3.2.0""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("На Windows:")
        ExplanationBlock(
            "RubyInstaller",
            "Это программа-установщик, которая автоматически настроит Ruby на вашем компьютере."
        )
        CodeBlock("""# Скачайте RubyInstaller с сайта:
# https://rubyinstaller.org/
# Запустите установщик и следуйте инструкциям""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("На Linux (Ubuntu/Debian):")
        CodeBlock("""sudo apt update
sudo apt install ruby-full

# Проверка установки (должна показать версию)
ruby -v   # => ruby 3.2.0...""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 5: Первая программа ===
        SectionTitle("Первая программа")
        BodyText("Создайте файл hello.rb и напишите:")
        ExplanationBlock(
            "Расширение .rb",
            "Файлы с кодом на Ruby обычно имеют расширение .rb. Это помогает компьютеру понять, что файл содержит код Ruby."
        )
        CodeBlock("""# hello.rb
puts "Привет, мир!"

# Запуск в терминале:
# ruby hello.rb""")
        Spacer(modifier = Modifier.height(12.dp))

        BodyText("Или запустите интерактивную консоль IRB (Interactive Ruby):")
        ExplanationBlock(
            "Что такое IRB?",
            "IRB — это интерактивная среда, где можно писать код и сразу видеть результат. Отлично подходит для экспериментов и обучения."
        )
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
        BodyText("Комментарии помогают объяснять код. Ruby игнорирует их при выполнении — они нужны только людям.")
        Spacer(modifier = Modifier.height(8.dp))

        ExplanationBlock(
            "Зачем нужны комментарии?",
            "Комментарии помогают вам и другим программистам понять, что делает код. Особенно полезно для сложной логики."
        )
        CodeBlock("""# Это однострочный комментарий
# Начинается с символа #

=begin
Это многострочный комментарий.
Он может занимать несколько строк.
Используется для длинных объяснений.
=end

# Хороший комментарий объясняет ПОЧЕМУ, а не ЧТО
# Плохо: x = x + 1  # увеличиваем x на 1
# Хорошо: x = x + 1  # переходим к следующему элементу массива""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 7: Переменные и типы данных ===
        SectionTitle("Переменные и типы данных")
        BodyText("В Ruby не нужно объявлять тип переменной — он определяется автоматически. Ruby — язык с динамической типизацией.")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Что такое переменная?",
            "Переменная — это как коробка с названием, в которой хранится значение. Вы можете положить туда число, текст или любой другой объект."
        )
        BodyText("Основные типы данных:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""# String (строка) — текст в кавычках
name = "Alice"
greeting = 'Привет'

# Integer (целое число)
age = 25
year = 2024

# Float (дробное число) — число с точкой
price = 19.99
pi = 3.14159

# Boolean (логический тип) — только true или false
is_active = true
is_deleted = false

# Symbol (символ) — лёгкая строка-идентификатор
# Используется для ключей в хэшах
status = :active
color = :red

# nil (отсутствие значения) — аналог null в других языках
nothing = nil

# Array (массив) — упорядоченный список элементов
fruits = ["яблоко", "банан", "апельсин"]
numbers = [1, 2, 3, 4, 5]

# Hash (хэш) — набор пар ключ-значение (как словарь)
user = {
  name: "Alice",
  age: 25,
  email: "alice@example.com"
}""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Всё является объектом",
            "В Ruby даже числа и nil — это объекты с методами. Это значит, что у них есть встроенные функции."
        )
        CodeBlock("""# У каждого объекта есть метод class (показывает тип)
25.class          # => Integer
"Hello".class     # => String

# У объектов есть методы (функции)
"Hello".length    # => 5 (длина строки)
"Hello".upcase    # => "HELLO" (в верхний регистр)
nil.class         # => NilClass

# Можно вызывать методы цепочкой
"Hello".upcase.length  # => 5

# Даже числа имеют методы!
5.times { puts "Ruby!" }  # напечатает 5 раз""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 8: Операторы ===
        SectionTitle("Операторы")
        BodyText("Операторы — это символы для выполнения действий над значениями.")
        Spacer(modifier = Modifier.height(8.dp))

        ExplanationBlock(
            "Арифметические операторы",
            "Используются для математических вычислений: сложения, вычитания, умножения и деления."
        )
        CodeBlock("""+  -  *  /     # сложение, вычитание, умножение, деление
%              # остаток от деления (10 % 3 => 1)
**             # возведение в степень (2 ** 3 => 8)

# Примеры:
5 + 3    # => 8
10 - 4   # => 6
2 * 6    # => 12
15 / 4   # => 3 (целочисленное деление)
15 % 4   # => 3 (остаток)
2 ** 10  # => 1024""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Операторы сравнения",
            "Сравнивают два значения и возвращают true или false. Используются в условиях."
        )
        CodeBlock("""==   # равно         (5 == 5   => true)
!=   # не равно      (5 != 3   => true)
>    # больше        (5 > 3    => true)
<    # меньше        (3 < 5    => true)
>=   # больше или равно (5 >= 5 => true)
<=   # меньше или равно (3 <= 5 => true)
<=>  # «космический корабль» 
     # возвращает -1, 0 или 1
     # (5 <=> 3 => 1, 3 <=> 5 => -1, 5 <=> 5 => 0)""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Логические операторы",
            "Используются для объединения нескольких условий."
        )
        CodeBlock("""&&   # И (AND) — оба условия должны быть true
     # (true && false => false)
     # (true && true => true)

||   # ИЛИ (OR) — хотя бы одно условие true
     # (true || false => true)
     # (false || false => false)

!    # НЕ (NOT) — инвертирует значение
     # (!true => false)
     # (!false => true)

# Также можно использовать слова:
and, or, not

# Пример:
age = 25
has_id = true

if age >= 18 && has_id
  puts "Можно войти"
end""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 9: Условные конструкции ===
        SectionTitle("Условные конструкции")
        BodyText("Условия позволяют выполнять код только при определённых обстоятельствах. Это как вопросы в реальной жизни: «Если идёт дождь, возьми зонт».")
        Spacer(modifier = Modifier.height(8.dp))

        ExplanationBlock(
            "Как работают условия?",
            "Ruby проверяет условие (true или false). Если true — выполняет код внутри блока. Если false — пропускает или выполняет альтернативный код."
        )
        CodeBlock("""# Базовый if/else
age = 18

if age >= 18
  puts "Совершеннолетний"  # выполнится, если age >= 18
else
  puts "Несовершеннолетний"  # выполнится, если age < 18
end

# Важно: условие должно заканчиваться словом end!""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Множественные условия (elsif)",
            "Когда нужно проверить несколько условий подряд. Ruby проверяет их по порядку и выполняет первое подходящее."
        )
        CodeBlock("""score = 85

if score >= 90
  puts "Отлично!"      # если score >= 90
elsif score >= 70
  puts "Хорошо"         # если 70 <= score < 90
elsif score >= 50
  puts "Удовлетворительно"  # если 50 <= score < 70
else
  puts "Не сдано"       # если score < 50
end""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Короткая запись (модификатор if)",
            "Если код короткий, можно написать условие в одну строку. Читается как обычный английский."
        )
        CodeBlock("""# Модификатор if (короткая запись)
puts "Взрослый" if age >= 18
# Читается: "Вывести 'Взрослый', если age >= 18"

# unless (обратный if) — выполняется, если условие FALSE
puts "Ребёнок" unless age >= 18
# Читается: "Вывести 'Ребёнок', если НЕ (age >= 18)" """)
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "case/when (аналог switch)",
            "Когда нужно сравнить одно значение с несколькими вариантами. Удобнее, чем много elsif."
        )
        CodeBlock("""day = "понедельник"

case day
when "понедельник"
  puts "Начало недели"
when "пятница"
  puts "Почти выходные!"
when "суббота", "воскресенье"  # можно перечислить несколько
  puts "Выходной!"
else
  puts "Обычный день"
end""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 10: Циклы ===
        SectionTitle("Циклы")
        BodyText("Циклы позволяют повторять действия многократно. Вместо того чтобы писать один и тот же код 10 раз, вы пишете его один раз и говорите Ruby повторить.")
        Spacer(modifier = Modifier.height(8.dp))

        ExplanationBlock(
            "Зачем нужны циклы?",
            "Представьте, что нужно напечатать числа от 1 до 100. Без цикла пришлось бы написать 100 строк кода. С циклом — всего 3 строки!"
        )
        CodeBlock("""# Цикл times — самый простой и идиоматичный в Ruby
# Повторяет блок N раз

3.times { puts "Ruby!" }
# Выведет:
# Ruby!
# Ruby!
# Ruby!

# Можно получить номер итерации (начинается с 0)
3.times do |i|
  puts "Итерация номер #{i}"
end
# Выведет:
# Итерация номер 0
# Итерация номер 1
# Итерация номер 2""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Цикл each — для работы с коллекциями",
            "Используется для перебора элементов массива или хэша. Каждый элемент по очереди передаётся в блок."
        )
        CodeBlock("""# Цикл each — перебирает элементы коллекции
fruits = ["яблоко", "банан", "апельсин"]

fruits.each do |fruit|
  puts "Я люблю #{fruit}"
end
# Выведет:
# Я люблю яблоко
# Я люблю банан
# Я люблю апельсин

# |fruit| — это переменная, которая получает текущий элемент
# do...end — блок кода (можно заменить на { ... })""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Цикл while — повторять пока условие true",
            "Выполняет код, пока условие остаётся истинным. Важно не забыть изменить условие, иначе цикл будет бесконечным!"
        )
        CodeBlock("""# Цикл while — повторяет, пока условие true
count = 0

while count < 5
  puts count
  count += 1  # ВАЖНО: увеличиваем count, иначе бесконечный цикл!
end
# Выведет: 0, 1, 2, 3, 4

# ⚠️ Опасно! Бесконечный цикл:
# while true
#   puts "Бесконечность!"
# end""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Цикл until — обратный while",
            "Повторяет код, пока условие FALSE. Останавливается, когда условие становится true."
        )
        CodeBlock("""# Цикл until — повторяет, пока условие FALSE
count = 0

until count >= 5
  puts count
  count += 1
end
# Выведет: 0, 1, 2, 3, 4
# Остановится, когда count станет >= 5""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Цикл for — классический цикл",
            "Похож на циклы в других языках. В Ruby используется реже, чем times и each."
        )
        CodeBlock("""# Цикл for — перебирает диапазон значений
for i in 1..5
  puts i
end
# Выведет: 1, 2, 3, 4, 5

# 1..5 — это диапазон от 1 до 5 (включительно)
# 1...5 — диапазон от 1 до 4 (5 не включается)""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Методы map и select — функциональный стиль",
            "Это специальные методы для преобразования и фильтрации коллекций. Они возвращают новый массив, не изменяя оригинал."
        )
        CodeBlock("""# Метод map — преобразует каждый элемент
numbers = [1, 2, 3, 4, 5]

doubled = numbers.map { |n| n * 2 }
# doubled => [2, 4, 6, 8, 10]
# numbers остался [1, 2, 3, 4, 5]

# Метод select — фильтрует элементы
evens = numbers.select { |n| n.even? }
# evens => [2, 4] (только чётные)

# Метод reject — обратный select
odds = numbers.reject { |n| n.even? }
# odds => [1, 3, 5] (только нечётные)""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 11: Методы ===
        SectionTitle("Методы (функции)")
        BodyText("Методы — это именованные блоки кода, которые можно вызывать многократно. Они помогают организовать код и избежать повторений.")
        Spacer(modifier = Modifier.height(8.dp))

        ExplanationBlock(
            "Зачем нужны методы?",
            "Если вам нужно выполнить одно и то же действие несколько раз, лучше написать метод. Это делает код чище и легче для поддержки."
        )
        CodeBlock("""# Определение метода
# def — начало метода, end — конец
def greet(name)
  "Привет, #{name}!"
end

# Вызов метода
puts greet("Alice")   # => Привет, Alice!
puts greet("Bob")     # => Привет, Bob!""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Параметры по умолчанию",
            "Можно задать значение по умолчанию для параметра. Если при вызове не передать значение, будет использовано значение по умолчанию."
        )
        CodeBlock("""# Метод с параметром по умолчанию
def greet(name = "мир")
  "Привет, #{name}!"
end

greet()        # => Привет, мир! (используется значение по умолчанию)
greet("Bob")   # => Привет, Bob! (передаём своё значение)""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Возврат значения",
            "Метод автоматически возвращает последнее выражение. Ключевое слово return обычно не нужно."
        )
        CodeBlock("""# Возврат значения (последнее выражение)
def square(x)
  x * x    # не нужен return! Ruby вернёт результат автоматически
end

puts square(5)   # => 25

# Явный return (обычно не нужен, но иногда полезен)
def absolute(x)
  return -x if x < 0  # если x отрицательный, вернуть -x
  x                    # иначе вернуть x
end

puts absolute(-5)   # => 5
puts absolute(5)    # => 5""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Методы с блоками",
            "Методы могут принимать блок кода. Ключевое слово yield выполняет этот блок."
        )
        CodeBlock("""# Метод с блоком
def repeat(n)
  n.times { yield }  # yield выполняет переданный блок
end

# Вызов с блоком
repeat(3) { puts "Hello!" }
# Выведет:
# Hello!
# Hello!
# Hello!""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 12: ООП ===
        SectionTitle("Объектно-ориентированное программирование")
        BodyText("Ruby — полностью объектно-ориентированный язык. Всё в Ruby — объект, включая числа, строки и даже nil.")
        Spacer(modifier = Modifier.height(8.dp))

        ExplanationBlock(
            "Что такое ООП?",
            "Объектно-ориентированное программирование — это подход, где код организуется вокруг объектов. Объект — это экземпляр класса, который содержит данные и методы."
        )
        BodyText("Классы и объекты:")
        Spacer(modifier = Modifier.height(4.dp))

        CodeBlock("""# Определение класса
# Класс — это чертёж для создания объектов
class Dog
  # Конструктор — вызывается при создании объекта
  def initialize(name, breed)
    @name = name      # @name — переменная экземпляра (хранится в объекте)
    @breed = breed
  end

  # Метод экземпляра — доступен для каждого объекта
  def bark
    "#{@name} говорит: Гав!"
  end

  # Геттер — метод для получения значения
  def name
    @name
  end
end

# Создание объекта (экземпляра класса)
dog = Dog.new("Бобик", "Лабрадор")
puts dog.bark   # => Бобик говорит: Гав!
puts dog.name   # => Бобик""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Наследование",
            "Класс может наследовать методы и свойства от другого класса. Дочерний класс получает всё от родительского и может добавлять своё."
        )
        CodeBlock("""# Наследование — создание нового класса на основе существующего
class GuideDog < Dog   # GuideDog наследует от Dog
  # Новый метод, которого нет в Dog
  def guide
    "#{@name} ведёт хозяина"
  end
end

guide = GuideDog.new("Рекс", "Овчарка")
puts guide.bark    # => Рекс говорит: Гав! (унаследованный метод)
puts guide.guide   # => Рекс ведёт хозяина (собственный метод)""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 13: Обработка ошибок ===
        SectionTitle("Обработка ошибок")
        BodyText("Ruby предоставляет механизмы для обработки исключений. Это помогает программе не падать при ошибках.")
        Spacer(modifier = Modifier.height(8.dp))

        ExplanationBlock(
            "Зачем обрабатывать ошибки?",
            "Если программа пытается разделить на ноль или открыть несуществующий файл, возникнет ошибка. Без обработки программа упадёт. С обработкой — можно показать понятное сообщение."
        )
        CodeBlock("""# begin/rescue — базовая обработка ошибок
begin
  result = 10 / 0  # здесь возникнет ошибка (деление на ноль)
rescue ZeroDivisionError => e
  # Этот код выполнится, если произошла ошибка деления на ноль
  puts "Ошибка: #{e.message}"
  # => Ошибка: divided by 0
rescue StandardError => e
  # Этот код выполнится для любой другой ошибки
  puts "Другая ошибка: #{e.message}"
ensure
  # Этот код выполнится ВСЕГДА, независимо от ошибки
  puts "Этот код выполнится всегда"
end""")
        Spacer(modifier = Modifier.height(12.dp))

        ExplanationBlock(
            "Короткая форма и собственные исключения",
            "Для простых случаев есть короткая запись. Также можно создавать свои типы ошибок."
        )
        CodeBlock("""# Короткая форма (для простых случаев)
result = 10 / 0 rescue 0   # => 0 (если ошибка, вернуть 0)

# Создание собственных исключений
class MyError < StandardError
  # Наследуем от StandardError
end

# Вызов исключения
raise MyError, "Что-то пошло не так"
# Это остановит программу и покажет ошибку""")
        Spacer(modifier = Modifier.height(24.dp))

        // === РАЗДЕЛ 14: Полезные советы ===
        SectionTitle("Полезные советы для начинающих")
        Spacer(modifier = Modifier.height(4.dp))

        TipItem("Используйте 2 пробела для отступов", "Не табуляцию — это стандарт Ruby. Помогает коду выглядеть единообразно.")
        TipItem("Именуйте переменные в snake_case", "my_variable, а не myVariable. Это соглашение Ruby.")
        TipItem("Именуйте классы в CamelCase", "MyClass, а не my_class. Первая буква заглавная.")
        TipItem("Избегайте лишних скобок", "puts \"Hello\" вместо puts(\"Hello\"). Код чище.")
        TipItem("Используйте символы для ключей хэша", "{ name: \"Alice\" } вместо { \"name\" => \"Alice\" }. Современный синтаксис.")
        TipItem("Читайте документацию", "ri — встроенная справка: ri String.split покажет документацию метода.")
        TipItem("Используйте IRB для экспериментов", "Быстрая проверка идей без создания файлов.")
        TipItem("Пишите тесты", "RSpec — самый популярный фреймворк для тестирования в Ruby.")

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
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = Color(0xFF333333),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ExplanationBlock(title: String, text: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFF0F8FF),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "💡 $title",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF333333)
            )
        }
    }
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