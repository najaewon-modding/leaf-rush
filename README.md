# Leaf Rush

Leaf Rush는 나무를 베었을 때 원목과 연결이 끊어진 나뭇잎이 빠르게 사라지도록 해주는 가벼운 NeoForge 모드입니다.

## Features

* 연결이 끊어진 나뭇잎을 빠르게 소멸
* 바닐라 Minecraft의 나뭇잎 decay 판정 사용
* 다른 나무의 원목과 연결된 나뭇잎은 유지
* 플레이어가 설치한 나뭇잎은 제외
* 별도의 복잡한 나무 탐색 없이 가볍게 동작

## How It Works

나무를 베면 주변 나뭇잎의 바닐라 decay 검사를 빠르게 실행합니다.
원목과 연결되어 있지 않은 나뭇잎만 사라지며, 소멸 과정이 주변 나뭇잎으로 연쇄적으로 이어집니다.

## Requirements

* Minecraft 26.1.2
* NeoForge
