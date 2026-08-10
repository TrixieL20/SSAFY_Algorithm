param(
    [Parameter(Mandatory=$true)]
    [string]$BranchName
)

# 1. 브랜치 생성
git switch -c $BranchName

# 2. 브랜치 이름에서 날짜 추출
#    26/03/21_HGD
#    ↓
#    26/03/21
$date = $BranchName.Split("_")[0]

# 3. / → -
#    26/03/21
#    ↓
#    26-03-21
$dateFolder = $date.Replace("/", "-")

# 4. 날짜 폴더 존재 확인
if (!(Test-Path $dateFolder)) {
    Write-Host "날짜 폴더가 없습니다: $dateFolder"
    exit 1
}

# 5. template 폴더 복사
Copy-Item "template" "$dateFolder/template" -Recurse

Write-Host "완료!"
Write-Host "브랜치: $BranchName"
Write-Host "생성된 폴더: $dateFolder/template"