github cli 설치
gh auth login

해당 깃 레포 위치로 이동 후 명령어 실행

pr 이름 기준으로 조회
gh pr list --state open --search "26/08/02"

pr 이름 기준으로 머지하는 명령어
gh pr list --state open --search "in:title 26/08/02" --json number --jq '.[].number' | ForEach-Object { gh pr merge $_ --squash --delete-branch }
