<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="홈" />
<%@ include file="../part/head.jspf"%>

<section class="section section-home-main flex-grow flex justify-center items-center">
	<div class="w-full max-w-md card-wrap">
		<div class="card bordered shadow-lg">
			<div class="card-title">
				<span>
					<i class="fas fa-sign-in-alt"></i>
				</span>
				<span>단축 URI</span>
			</div>

			<div class="px-4 py-4">
				<script>
					let ShortUri__submitDone = false;
					function ShortUri__submit(form) {
						if (ShortUri__submitDone) {
							return;
						}
						
						form.originUri.value = form.originUri.value.trim();

						if (form.originUri.value.length == 0) {
							alert('주소를 입력해주세요.');
							form.originUri.focus();

							return;
						}
						
						form.submit();
						ShortUri__submitDone = true;
					}
				</script>
				<form action="../a/doShortUri" method="POST" onsubmit="ShortUri__submit(this); return false;">
					<input type="hidden" name="redirectUri" value="${param.afterLoginUri}" />

					<div class="form-control">
						<label class="label">
							<span class="label-text">페이지 주소</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100" name="originUri" type="text" placeholder="페이지주소를 입력해주세요."/>
						</div>
					</div>

					<div class="form-control">
						<label class="label">
							<span class="label-text">페이지 설명</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100" name="text" type="text" placeholder="페이지 설명글을 입력해주세요." />
						</div>
					</div>

					<div class="btns">
						<button type="submit" class="btn btn-link">주소 줄이기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../part/foot.jspf"%>