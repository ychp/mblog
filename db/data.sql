INSERT INTO `sky_user` (`id`, `name`, `nick_name`, `password`, `salt`, `status`, `created_at`, `updated_at`)
VALUES
	(1, 'admin', 'admin', 'ZCyqCBiFf5ky6m/BeFJFyGuk6LSVlu5f', 'grqx5iCM2Ma8KT9x1hja6acW', 1, now(), now());

INSERT INTO `role` (`id`, `name`, `status`, `created_at`, `updated_at`)
VALUES
	(1, 'root', 1, '2017-08-27 00:00:00', '2017-08-27 00:00:00'),
	(2, 'user', 1, '2017-08-27 00:00:00', '2017-08-27 00:00:00');

INSERT INTO `user_role` (`user_id`, `role_id`, `role_name`, `created_at`, `updated_at`)
VALUES
	(1, 1, 'root', '2017-08-27 00:00:00', '2017-08-27 00:00:00');
