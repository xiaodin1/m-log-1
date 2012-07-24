<div id="sidebar-right" class="sidebar">
	<div class="clear-block block block-catalog">
		<h2>分类目录</h2>
		<div class="content">
			<div class="item-list" id="item-list-category">
				<ul>
					<@widget.placeholder path="/widget/listCatalog" />
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>最新文章</h2>
		<div class="content">
			<div class="item-list" id="item-list-top">
				<ul>
					<@widget.placeholder path="/widget/recentPost" />
				</ul>
			</div>
		</div>
	</div>
	
	<#--
	<div class="clear-block block block-catalog">
		<h2>${recentComment}</h2>
		<div class="content">
			<div class="item-list" id="item-list-topcomment">
				<ul>
					${widget("RecentCommentWidget", "20")}
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>${friendLinks}</h2>
		<div class="content">
			<div class="item-list" id="item-list-topcomment">
				<ul>
					${widget("LinkListWidget")}
				</ul>
			</div>
		</div>
	</div>
	-->
</div>