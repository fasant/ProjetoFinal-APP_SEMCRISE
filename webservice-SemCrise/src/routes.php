<?php
// Routes

$app->get('/posto/list', function ($request, $response) {

	$db = $this->db;
	foreach($db->query('SELECT * FROM posto') as $row){
		$return[] = $row;
	};

	return $response->withJson($return);
});


$app->get('/posto/list/ordemPreco', function ($request, $response) {

	$db = $this->db;
	foreach($db->query('SELECT * FROM posto ORDER BY preco') as $row){
		$return[] = $row;
	};

	return $response->withJson($return);
});


$app->get('/posto/list/ordemId', function ($request, $response) {

	$db = $this->db;
	foreach($db->query('SELECT * FROM posto ORDER BY id') as $row){
		$return[] = $row;
	};

	return $response->withJson($return);
});

$app->get('/posto/listPorLocal/{local}', function ($request, $response) {
	$local = $request->getAttribute('local');

	$db  = $this->db;
	$sth = $db->prepare('SELECT * FROM posto WHERE local = ? ORDER BY preco');

	$sth->execute(array($local));

	$return = [];

	foreach($sth as $row){
		$return[] = $row;
	}

	return $response->withJson($return);
});

$app->post('/posto/new', function ($request, $response) {

	$db = $this->db;
	$sth = $db->prepare("INSERT INTO posto VALUES (DEFAULT, :nome, :local, :produto, :preco)");

	$posto = $request->getParsedBody();
	
	unset($posto['id']);
	$sth->execute($posto);
	return $response->withJson($posto); 

});

$app->get('/posto/delete/{id}', function ($request, $response) {
	
	$id = $request->getAttribute('id');

	$db  = $this->db;
	$sth = $db->prepare('DELETE FROM posto WHERE id = ?');

	$sth->execute(array($id));

	$return = [];

	foreach($sth as $row){
		$return[] = $row;
	}

	return $response->withJson($return);
});