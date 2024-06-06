-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Июн 06 2024 г., 06:50
-- Версия сервера: 5.7.24
-- Версия PHP: 8.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `somnium`
--

-- --------------------------------------------------------

--
-- Структура таблицы `responses`
--

CREATE TABLE `responses` (
  `id` bigint(20) NOT NULL,
  `date_of_response` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_full_name` varchar(255) DEFAULT NULL,
  `user_phone_number` varchar(255) DEFAULT NULL,
  `user_summary` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `vacancy_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `responses`
--

INSERT INTO `responses` (`id`, `date_of_response`, `user_email`, `user_full_name`, `user_phone_number`, `user_summary`, `user_id`, `vacancy_id`) VALUES
(1, '06.06.2024 11:38', 'asd@asd.asd', NULL, '1234567890', NULL, NULL, NULL),
(2, '06.06.2024 11:38', 'asd@asd.asd', NULL, '1234567890', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `date_of_creation_account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `date_of_birth`, `date_of_creation_account`, `email`, `full_name`, `login`, `password`, `phone_number`, `role`) VALUES
(1, NULL, '06.06.2024', 'asd@asd.asd', NULL, 'admin', '$2a$08$PTxqzaYSDQ6c3Sk.JGpZ..dKzBwWENo9rDT5mE/HqKIufCDuGbo3W', '1234567890', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Структура таблицы `vacancies`
--

CREATE TABLE `vacancies` (
  `id` bigint(20) NOT NULL,
  `date_of_vacancy_creation` varchar(255) DEFAULT NULL,
  `is_vacancy_open` bit(1) DEFAULT NULL,
  `vacancy_city_location` varchar(255) DEFAULT NULL,
  `vacancy_description` varchar(10000) DEFAULT NULL,
  `vacancy_name` varchar(255) DEFAULT NULL,
  `vacancy_salary` int(11) DEFAULT NULL,
  `vacancy_subdivision` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `vacancies`
--

INSERT INTO `vacancies` (`id`, `date_of_vacancy_creation`, `is_vacancy_open`, `vacancy_city_location`, `vacancy_description`, `vacancy_name`, `vacancy_salary`, `vacancy_subdivision`) VALUES
(1, '06.06.2024', b'1', 'Кокшетау', 'Просто описание вакансиии', 'Java developer', 50, 'Разработчик'),
(2, '06.06.2024', b'1', 'Астана', 'Просто описание вакансии', 'Просто какая то вакансия', 55000, 'HR');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `responses`
--
ALTER TABLE `responses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqf8rt9h0wd5pmaxouhxqsoeuq` (`user_id`),
  ADD KEY `FK9lipsp15yhtdsuyln7dr3k5hm` (`vacancy_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `vacancies`
--
ALTER TABLE `vacancies`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `responses`
--
ALTER TABLE `responses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `vacancies`
--
ALTER TABLE `vacancies`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `responses`
--
ALTER TABLE `responses`
  ADD CONSTRAINT `FK9lipsp15yhtdsuyln7dr3k5hm` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancies` (`id`),
  ADD CONSTRAINT `FKqf8rt9h0wd5pmaxouhxqsoeuq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
